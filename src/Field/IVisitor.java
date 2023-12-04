package Field;

public interface IVisitor
{
    boolean visit(Property propertyField);
    boolean visit(Service serviceField);
    boolean visit(Lucky luckyField);
}
