package connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import models.OrdersViewer;
import models.PaymentMethod;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer>getAllOrdersViewers(SQLiteDatabase database)
    {
        StringBuilder sqlBuilder=new StringBuilder();
        sqlBuilder.append(" SELECT ");
        sqlBuilder.append(" Orders.Id AS OrderId, ");
        sqlBuilder.append(" Orders.Code AS OrderCode, ");
        sqlBuilder.append(" Customer.Name AS CustomerName, ");
        sqlBuilder.append(" Employee.Name AS EmployeeName, ");
        sqlBuilder.append(" Orders.OrderDate, ");

        //sqlBuilder.append("         -- Tổng giá gốc ");
        sqlBuilder.append(" ROUND(SUM(OrderDetails.Price * OrderDetails.Quantity), 2) AS GrossTotal, ");

        //sqlBuilder.append(" -- Tổng Discount thực tế ");
        sqlBuilder.append(" ROUND(SUM(OrderDetails.Price * OrderDetails.Quantity * OrderDetails.Discount/100), 2) AS TotalDiscount, ");

        //sqlBuilder.append(" -- Tổng VAT sau khi trừ Discount ");
        sqlBuilder.append(" ROUND(SUM((OrderDetails.Price * OrderDetails.Quantity * (1 - OrderDetails.Discount/100)) * OrderDetails.VAT/100), 2) AS TotalVAT, ");

        //sqlBuilder.append(" -- Tổng tiền thanh toán cuối cùng ");
        sqlBuilder.append(" ROUND(SUM((OrderDetails.Price * OrderDetails.Quantity * (1 - OrderDetails.Discount/100)) * (1 + OrderDetails.VAT/100)), 2) AS FinalTotal ");
        sqlBuilder.append(" FROM Orders ");

        sqlBuilder.append(" JOIN Customer ON Orders.CustomerId = Customer.Id ");
        sqlBuilder.append(" JOIN Employee ON Orders.EmployeeId = Employee.Id ");
        sqlBuilder.append(" JOIN OrderDetails ON Orders.Id = OrderDetails.OrderId ");
        sqlBuilder.append(" GROUP BY Orders.Id; ");

        String sql=sqlBuilder.toString();

        Cursor cursor = database.rawQuery(sql,
                null);
        ArrayList<OrdersViewer> datasets=new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String ordercode = cursor.getString(1);
            String cust_name = cursor.getString(2);
            String employee_name = cursor.getString(3);
            String orderdate = cursor.getString(4);
            double grosstotal = cursor.getDouble(5);
            double totaldiscount = cursor.getDouble(6);
            double totalVAT = cursor.getDouble(7);
            double FinalTotal = cursor.getDouble(8);

            OrdersViewer ov = new OrdersViewer();
            ov.setId(id);
            ov.setCode(ordercode);
            ov.setCustomerName(cust_name);
            ov.setEmployeeName(employee_name);
            ov.setOrderDate(orderdate);
            ov.setCrossTotal(grosstotal);
            ov.setTotalDiscount(totaldiscount);
            ov.setTotalVAT(totalVAT);
            ov.setFinalTotal(FinalTotal);

            datasets.add(ov);
        }
        cursor.close();
        return datasets;

    }
}
