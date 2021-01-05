package uftdeveloper.sap.utils;


public class InitializeStaticVariables {
    private static String salesOrder = null;

    private InitializeStaticVariables() {
        throw new IllegalStateException("Initialize Static Variable Class");
    }

    public static void resetStaticVariables() {
        setSalesOrder(null);
    }

    public static String getSalesOrder() {
        return salesOrder;
    }

    public static void setSalesOrder(String salesOrder) {
        InitializeStaticVariables.salesOrder = salesOrder;
    }
}
