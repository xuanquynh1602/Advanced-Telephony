package models;

public class OrdersViewer extends Orders{
    private String customerName;
    private String employeeName;
    private double CrossTotal;
    private double TotalDiscount;
    private double TotalVAT;
    private double FinalTotal;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public double getCrossTotal() {
        return CrossTotal;
    }

    public void setCrossTotal(double crossTotal) {
        CrossTotal = crossTotal;
    }

    public double getTotalDiscount() {
        return TotalDiscount;
    }

    public void setTotalDiscount(double totalDiscount) {
        TotalDiscount = totalDiscount;
    }

    public double getTotalVAT() {
        return TotalVAT;
    }

    public void setTotalVAT(double totalVAT) {
        TotalVAT = totalVAT;
    }

    public double getFinalTotal() {
        return FinalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        FinalTotal = finalTotal;
    }
}
