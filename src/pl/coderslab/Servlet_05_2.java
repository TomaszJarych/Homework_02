package pl.coderslab;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pl.coderslab.Servlet_05_1.Product;

@WebServlet("/Servlet_05_2")
public class Servlet_05_2 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String result = "";
		double totalSum = 0;
		try {
			ArrayList<Product> productList = (ArrayList<Product>) httpSession.getAttribute("productList");
			if (productList.isEmpty()) {
				result = "<h1>Brak produktów</h1>";
			} else {
				for (Product product : productList) {
					result += "<h1>" + product.toString() + "</h1>" + "\n";
					totalSum += product.getTotalPrice();
				}
				result += "<h1>" + "SUMA TO : " + totalSum + "</h1>";

			}

			resp.getWriter().append(result);
		} catch (NullPointerException e) {
			resp.getWriter().append("<h1>Brak produktow!</h1>");
		}
	}
}
