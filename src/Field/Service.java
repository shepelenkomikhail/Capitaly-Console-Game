package Field;

public class Service extends Field
{
    private final int moneyToPay;
    public Service(int moneyToPay) {
        this.moneyToPay = moneyToPay;
    }   //Constructor
    public int getMoneyToPay() {
        return moneyToPay;
    }
    @Override
    public boolean accept(IVisitor visitor) {
        return visitor.visit(this);
    }
    @Override public String toString() {
        return "S("  + moneyToPay + ")";
    }       //Shows when player pays for the service

}
