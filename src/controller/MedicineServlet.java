
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Medicine;
import model.User;
import service.MedicineService;

@WebServlet("/medicines")
public class MedicineServlet extends HttpServlet {

    private MedicineService medicineService;

    @Override
    public void init() throws ServletException {
        medicineService = new MedicineService();
    }

    private boolean isLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null &&
               session.getAttribute("loggedInUser") != null;
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                               "Please login first.");
            return;
        }

        List<Medicine> medicines = medicineService.getAllMedicines();

        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        out.print("[");

        for (int i = 0; i < medicines.size(); i++) {
            Medicine m = medicines.get(i);

            if (i > 0) out.print(",");

            out.print("{");
            out.print("\"medicineId\":" + m.getMedicineId() + ",");
            out.print("\"medicineName\":\"" + escapeJson(m.getMedicineName()) + "\",");
            out.print("\"manufacturer\":\"" + escapeJson(m.getManufacturer()) + "\",");
            out.print("\"category\":\"" + escapeJson(m.getCategory()) + "\",");
            out.print("\"price\":" + m.getPrice() + ",");
            out.print("\"quantity\":" + m.getQuantity() + ",");
            out.print("\"expiryDate\":\"" + escapeJson(m.getExpiryDate()) + "\"");
            out.print("}");
        }

        out.print("]");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        request.setCharacterEncoding("UTF-8");

        try {
            String name = request.getParameter("medicineName");
            String manufacturer = request.getParameter("manufacturer");
            String category = request.getParameter("category");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            String expiryDate = request.getParameter("expiryDate");

            Medicine medicine = new Medicine(
                0, name, manufacturer, category,
                price, quantity, expiryDate
            );

            boolean added = medicineService.addMedicine(medicine);

            sendResult(response, added,
                       added ? "Medicine added successfully." :
                               "Could not add medicine. Check the entered details.");

        } catch (Exception e) {
            sendResult(response, false, "Invalid medicine details.");
        }
    }

    @Override
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
                         throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        request.setCharacterEncoding("UTF-8");

        try {
            int id = Integer.parseInt(request.getParameter("medicineId"));
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            boolean updated =
                medicineService.updateMedicine(id, price, quantity);

            sendResult(response, updated,
                       updated ? "Medicine updated successfully." :
                                 "Medicine could not be updated.");

        } catch (Exception e) {
            sendResult(response, false, "Invalid update details.");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
                            throws ServletException, IOException {

        if (!isLoggedIn(request)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("medicineId"));

            boolean deleted = medicineService.deleteMedicine(id);

            sendResult(response, deleted,
                       deleted ? "Medicine deleted successfully." :
                                 "Medicine could not be deleted.");

        } catch (Exception e) {
            sendResult(response, false, "Invalid medicine ID.");
        }
    }

    private void sendResult(HttpServletResponse response,
                            boolean success,
                            String message) throws IOException {

        response.setContentType("application/json;charset=UTF-8");

        response.getWriter().print(
            "{\"success\":" + success +
            ",\"message\":\"" + escapeJson(message) + "\"}"
        );
    }

    private String escapeJson(String value) {
        if (value == null) return "";

        return value.replace("\\", "\\\\")
                    .replace("\"", "\\\"")
                    .replace("\n", "\\n")
                    .replace("\r", "\\r")
                    .replace("\t", "\\t");
    }
}

