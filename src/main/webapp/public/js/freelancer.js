// Freelancer Theme JavaScript

(function($) {
    "use strict"; // Start of use strict

    // jQuery for page scrolling feature - requires jQuery Easing plugin
    $('.page-scroll a').bind('click', function(event) {
        var $anchor = $(this);
        $('html, body').stop().animate({
            scrollTop: ($($anchor.attr('href')).offset().top - 50)
        }, 1250, 'easeInOutExpo');
        event.preventDefault();
    });

    // Highlight the top nav as scrolling occurs
    $('body').scrollspy({
        target: '.navbar-fixed-top',
        offset: 51
    });

    // Closes the Responsive Menu on Menu Item Click
    $('.navbar-collapse ul li a').click(function(){ 
            $('.navbar-toggle:visible').click();
    });

    // Offset for Main Navigation
    $('#mainNav').affix({
        offset: {
            top: 100
        }
    })

    // Floating label headings for the contact form
    $(function() {
        $("body").on("input propertychange", ".floating-label-form-group", function(e) {
            $(this).toggleClass("floating-label-form-group-with-value", !!$(e.target).val());
        }).on("focus", ".floating-label-form-group", function() {
            $(this).addClass("floating-label-form-group-with-focus");
        }).on("blur", ".floating-label-form-group", function() {
            $(this).removeClass("floating-label-form-group-with-focus");
        });
        
        
        $("#analize").click(function(e){
        	
  
        	var idTemp= "d"+(new Date()).getTime();

          	
        	
          	$('<iframe></iframe>')
            .attr({ "id": idTemp,
        		"name":idTemp,
        		"src":"",
        		"class":"iframeHidden"
        		
        		}).appendTo('#sandbox');
        	
          	
        	loadIframe(idTemp);
        	
        	$("#form").attr({ "target" : idTemp }).submit();

        })
        
        
        
    });
    
    function callbackD3(data){
    	   
        //--debugger;
        if(data!=null && data.length>0)
        {
          renderD3("container",data);
          $('#portfolioModal1').modal('show');
        }
        else{
          alert("Please, you validate your code in https://remix.ethereum.org/ ");
        }
      }

    
    
    function loadIframe(idFrame){
    	
       // var iframe = $("#"+idFrame);
      
        
        $("#"+idFrame).load(function(el){
            var rg= new RegExp("^<.+>(.+)<\\/.+>$","g")
            var text=$("#"+idFrame).contents().find('body').text();
            
          
            	
            var match = rg.exec(text);
           
            try{
            	if(match==null){
            		
            		 var dataClean=$.parseJSON(text);
            		 callbackD3( dataClean);
            	}
            	else
            	{
            		 var dataClean=$.parseJSON(match[1]);
            		 callbackD3( dataClean);
            	}

            }
            catch (e) {
				// TODO: handle exception
            	callbackD3( null);
			}
           
            
            
        });
        
        
       
    	
    }
    
    
    
    
    
    
    
    
    function renderD3(_elReact,dataRender){

        var treeData={
            "name": ""
              ,"factor":0.5
              ,"children": dataRender
            }


                
            var _radius=16;
            var _radiusOut=1e-4;
            
                  var colors = 
            {
              "contractSolc":"blue"
              ,"variableSolc":"yellow"
              ,"functionSolc":"green"
              ,"modifiedSolc":"red"  
               ,"eventSolc":"pink"    
            }	
            /*
            d3.scaleOrdinal()
              .range(["#98abc5", "#8a89a6"]);
              */
              
              

              
          var pie = d3.pie()
              .sort(null)
              .value(function(d) { return d; });
              
              
              // Set the dimensions and margins of the diagram
          var margin = {top: 20, right: 90, bottom: 30, left: 90},
              width = 960 - margin.left - margin.right,
              height = 800 - margin.top - margin.bottom;

          // append thesvg object to the body of the page
          // appends a 'group' element to 'svg'
          // moves the 'group' element to the top left margin
          d3.select("#"+_elReact).select("svg").remove();
          var svg = d3.select("#"+_elReact).append("svg")
              .attr("width", width + margin.right + margin.left)
              .attr("height", height + margin.top + margin.bottom)
            .append("g")
              .attr("transform", "translate("
                    + margin.left + "," + margin.top + ")");

          var i = 0,
              duration = 750,
              root;

          // declares a tree layout and assigns the size
          var treemap = d3.tree().size([height, width]);

          // Assigns parent, children, height, depth
          root = d3.hierarchy(treeData, function(d) { return d.children; });
          root.x0 = height / 2;
          root.y0 = 0;

          // Collapse after the second level
          //--root.children.forEach(collapse);

          update(root);

          // Collapse the node and all it's children
          function collapse(d) {
            if(d.children) {
              d._children = d.children
              d._children.forEach(collapse)
              d.children = null
            }
          }

          function update(source) {

            // Assigns the x and y position for the nodes
            var treeData = treemap(root);

            // Compute the new tree layout.
            var nodes = treeData.descendants(),
                links = treeData.descendants().slice(1);

            // Normalize for fixed-depth.
            nodes.forEach(function(d){ d.y = d.depth * 180});

          /*
          var filtro=[];
          for(var it=0;it<nodes.length;it++)
          {
            var d=nodes[it];
            if(d.id || (d.id = ++i) )
            {
              var obj=d;
              obj.data.idLocalElm=it;
              filtro.push(obj); 	
            }
          }
          */
            // ****************** Nodes section ***************************

            // Update the nodes...
            var node = svg.selectAll('g.node')
                    /*.data(filtro)*/
                .data(nodes, function(d) {return d.id || (d.id = ++i); });

            // Enter any new modes at the parent's previous position.
            var nodeEnter = node.enter().append('g')
                .attr('class', 'node')
                .attr("transform", function(d) {
                  return "translate(" + source.y0 + "," + source.x0 + ")";
              })
              .on('click', click);








          /*
          // Add Pie for the nodes
          nodeEnter.append("g")
                .attr('class', function(d){
                return 'pathList '+ "idEllocal"+d.data.idLocalElm;
                } );
              
              
              

              
              for(var it=0;it<filtro.length;it++)
              {
              if(filtro[it].data.pieData!=undefined)
              {
            
                  
              
                  d3.select("g.pathList.idEllocal"+filtro[it].data.idLocalElm)
                  .selectAll("g")
                .data(pie(filtro[it].data.pieData))
                .enter()
                .append("path")
                .attr("d", d3.arc()
                  .outerRadius((_radius*filtro[it].data.factor))
                  .innerRadius(_radius)
                  )
                .style("fill",function(d) { return color(d.data); })
                .attr('class', 'ePath');
                
              }

              }
            */  
            // Add Circle for the nodes
            nodeEnter.append('circle')
                .attr('class', 'node')
                .attr('r', _radius)
                .style("fill", function(d) {
                

            var colorOuput;
            if(d.data.typeNode==undefined){
            colorOuput="#fff";
            }
            else if(d.data.typeNode=="FNT"){
              colorOuput=colors.functionSolc;
            }
            else if(d.data.typeNode=="EVENT"){
              colorOuput=colors.eventSolc;
            }
            else if(d.data.typeNode=="VAR"){
              colorOuput=colors.variableSolc;
            }
            
            else if(d.data.typeNode=="MOD"){
              colorOuput=colors.modifiedSolc;
            }
            else if(d.data.typeNode=="CONTRC"){
              colorOuput=colors.contractSolc;
            }
            
                  // return d._children ? "lightsteelblue" : "#fff";
                  return colorOuput;
            })
            
            .attr('class',function (d){
            
                if(d.data.externData!=null){
                  return "externalContract";
                }
                return "localContract";
            })
            ;


            // Add labels for the nodes
            nodeEnter.append('text')
                .attr("dy", ".35em")
                .attr("x", function(d) {
                    return d.children || d._children ? -1*_radius*d.data.factor -5  : _radius*d.data.factor + 5;
                })
                .attr("text-anchor", function(d) {
                    return d.children || d._children ? "end" : "start";
                })
                .text(function(d) {
                    var textData="";
                    if(d.data.count!=undefined)
                    {
                        textData= "["+d.data.count+"]"+d.data.name;
                        if(d.data.externData!=null)
                        {
                          textData=textData+":Contract->"+d.data.externData;
                        }
                    }
                    else
                    {
                      textData= d.data.name;
                    }
                    
                    return textData;
                
                });

            // UPDATE
            var nodeUpdate = nodeEnter.merge(node);

            // Transition to the proper position for the node
            nodeUpdate.transition()
              .duration(duration)
              .attr("transform", function(d) { 
                  return "translate(" + d.y + "," + d.x + ")";
              });

            // Update the node attributes and style
            nodeUpdate.select('circle.node')
              .attr('r', _radius)
              .style("fill", function(d) {
              
              var colorOuput;
            if(d.data.typeNode==undefined){
            colorOuput="#fff";
            }
            else if(d.data.typeNode=="FNT"){
              colorOuput=colors.functionSolc;
            }
            else if(d.data.typeNode=="EVENT"){
              colorOuput=colors.eventSolc;
            }

            else if(d.data.typeNode=="VAR"){
              colorOuput=colors.variableSolc;
            }
            
            else if(d.data.typeNode=="MOD"){
              colorOuput=colors.modifiedSolc;
            }
            else if(d.data.typeNode=="CONTRC"){
              colorOuput=colors.contractSolc;
            }
            
                  // return d._children ? "lightsteelblue" : "#fff";
                  return colorOuput;
              /*
                  if(d.data.colorLeaf!=undefined )
                  {
                      return d.data.colorLeaf;
                  }
                  else
                  {
                    return d._children ? "lightsteelblue" : "#fff";
                  }
              */
              
              
              })
              .attr('cursor', 'pointer');


            // Remove any exiting nodes
            var nodeExit = node.exit().transition()
                .duration(duration)
                .attr("transform", function(d) {
                    return "translate(" + source.y + "," + source.x + ")";
                })
                .remove();

            // On exit reduce the node circles size to 0
            nodeExit.select('circle')
              .attr('r', 1e-6);

            // On exit reduce the opacity of text labels
            nodeExit.select('text')
              .style('fill-opacity', 1e-6);

            // ****************** links section ***************************

            // Update the links...
            var link = svg.selectAll('path.link')
                .data(links, function(d) { return d.id; });

            // Enter any new links at the parent's previous position.
            var linkEnter = link.enter().insert('path', "g")
                .attr("class", "link")
                .attr('d', function(d){
                  var o = {x: source.x0, y: source.y0}
                  return diagonal(o, o)
                });

            // UPDATE
            var linkUpdate = linkEnter.merge(link);

            // Transition back to the parent element position
            linkUpdate.transition()
                .duration(duration)
                .attr('d', function(d){ return diagonal(d, d.parent) });

            // Remove any exiting links
            var linkExit = link.exit().transition()
                .duration(duration)
                .attr('d', function(d) {
                  var o = {x: source.x, y: source.y}
                  return diagonal(o, o)
                })
                .remove();

            // Store the old positions for transition.
            nodes.forEach(function(d){
              d.x0 = d.x;
              d.y0 = d.y;
            });

            // Creates a curved (diagonal) path from parent to the child nodes
            function diagonal(s, d) {

              var path = `M ${s.y} ${s.x}
                      C ${(s.y + d.y) / 2} ${s.x},
                        ${(s.y + d.y) / 2} ${d.x},
                        ${d.y} ${d.x}`

              return path
            }

            // Toggle children on click.
            function click(d) {
              if (d.children) {
                  d._children = d.children;
                  d.children = null;
                } else {
                  d.children = d._children;
                  d._children = null;
                }
              update(d);
            }
          }
       

      }
    
    
    

})(jQuery); // End of use strict
