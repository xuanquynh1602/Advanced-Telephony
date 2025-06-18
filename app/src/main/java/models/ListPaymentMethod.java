package models;

import java.util.ArrayList;

public class ListPaymentMethod {
    ArrayList<PaymentMethod>paymentMethods;
    public ListPaymentMethod()
    {
        paymentMethods=new ArrayList<>();
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
    public void gen_payment_method()
    {
        paymentMethods.add(new PaymentMethod(1,"Banking Account", "Chuyển khoản ngân hàng"));
        paymentMethods.add(new PaymentMethod(1,"MOMO", "Dùng Ví điện tử MOMO"));
        paymentMethods.add(new PaymentMethod(1,"Cash", "Trả bằng tiền mặt"));
        paymentMethods.add(new PaymentMethod(1,"COD", "Xem hàng mới trả tiền"));

    }
}
