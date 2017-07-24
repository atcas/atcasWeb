contract MyContract {
  uint balance;
  event event1(uint d);
  function MyContract() {
    Mint(1000000);
    event1(7);
  }

  function Mint(uint amount) internal {
    balance = amount;
  }

  function Withdraw() {
    msg.sender.send(balance);
  }

  function GetBalance() constant returns(uint) {
    return balance;
  }
}