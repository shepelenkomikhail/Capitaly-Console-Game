package Field;

public class Lucky extends Field
{
    private final int recievedMoney;
    public Lucky(int recievedMoney) {this.recievedMoney = recievedMoney;}
    public int getRecievedMoney() {return recievedMoney;}
    @Override
    public boolean accept(IVisitor visitor) {return  visitor.visit(this);}
    @Override public String toString() {return "L(" + recievedMoney + ")";}         //Shows when player get lucky money

}
