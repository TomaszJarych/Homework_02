package pl.coderslab;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Servlet_05_1")
public class Servlet_05_1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		String htmlStart = "<html><body>";
		String form = "<form action=\"http://localhost:8080/Homework_02/Servlet_05_1\" method=\"post\"><div><input type=\"text\" name=\"product_name\" id=\"product_name\"><label for=\"product_name\">Wpisz nazwe produktu </label></div><div><input type=\"number\" name=\"quantity\" id=\"quantity\"><label for=\"quantity\">Podaj ilosc</label></div><div><input type=\"number\" step=\"0.01\" name=\"price\" id=\"price\"><label for=\"price\">Podaj cene </label></div><div><input type=\"submit\" value=\"Wyslij\"></div></form>";
		String htmlEnd = "</body></html>";
		String output = "";
		if (httpSession.isNew()) {
			ArrayList<Product> productList = new ArrayList<>();
			httpSession.setAttribute("productList", productList);
			output = htmlStart + form + htmlEnd;
		}else {
			String linkToBasket = " <div><h1><a href=\"http://localhost:8080/Homework_02/Servlet_05_2\">Pokaz koszyk</a></h1></div>";
			output = htmlStart+form+linkToBasket+htmlEnd;
		}

		resp.getWriter().append(output);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HttpSession httpSession = req.getSession();
			String product = req.getParameter("product_name");
			String quantityString = req.getParameter("quantity");
			String priceString = req.getParameter("price");
			ArrayList<Product> productList = (ArrayList<Product>) httpSession.getAttribute("productList");
			int quantity = Integer.parseInt(quantityString);
			double price = Double.parseDouble(priceString);
			String output = "";
			if (quantity > 0 && price > 0 && Objects.nonNull(product)) {
				Product prod = new Product(product, quantity, price);
				productList.add(prod);
				output = "<h1>Produkt dodano</h1>";
				httpSession.setAttribute("productList", productList);
			}else {
				output = "<h1>Błedne dane. Produkt nie zostal dodany</h1>";
				
			}
			resp.getWriter().append(output);
			doGet(req, resp);
		} catch (NullPointerException | NumberFormatException e) {
			resp.getWriter().append("<h1>Blad!</h1>");
			doGet(req, resp);
		}

	}

	protected class Product {
		private String name;
		private int quantity;
		private double pricePerItem;
		private double totalPrice;

		public Product(String name, int quantity, double pricePerItem) {
			this.name = name;
			this.quantity = quantity;
			this.pricePerItem = pricePerItem;
			this.totalPrice = quantity * pricePerItem;
		}

		public String getName() {
			return name;
		}

		public int getQuantity() {
			return quantity;
		}

		public double getPricePerItem() {
			return pricePerItem;
		}

		public double getTotalPrice() {
			return totalPrice;
		}

		@Override
		public String toString() {
			return  name + ", ilosc :" + quantity + ", Cena za sztuke : " + pricePerItem + ", Cena za wszystkie sztuki : "
					+ totalPrice;
		}
	}
}

// #### Zadanie 5
// W projekcie stwórz servlet `Servlet_05_1` oraz `Servlet_05_2`. Celem zadania
// jest przechowywanie koszyka zakupowego.
// 1. Stwórz formularz z polem tekstowym (nazwa) oraz dwoma numerycznymi (ilość
// i cena) służącymi do dodawania produktu do koszyka. Formularz powinien być
// przesłany na tą samą stronę metodą `POST`.
// 2. Po przesłaniu danych metodą POST przesłany produkt wraz z ceną i ilością
// dodaj do sesji pod kluczem `basket`. Pamiętaj, iż klucz `basket` w sesji musi
// przechowywać więcej niż `1` produkt (użyj tablicy). Po dodaniu elementu do
// koszyka wyświetl komunikat `Produkt dodany` i formularz służący do dodanie
// następnego produktu (taki sam jak w punkcie 1).
// 3. Na stronie formularza dodaj odnośnik do strony wyświetlającej zawartość
// koszyka.
// 4. W servlecie `Servlet_05_2` wyświetl zawartość koszyka oraz sumę cen
// produktów, pamiętaj iż każdy produkt ma dodaną ilość.
//
// Przykład - strona koszyka:
// ```
// Produkt 1 - 4 x 5.20zł = 20.80zł
// Produkt 2 - 1 x 9.99zł = 9.99zł
// Produkt 3 - 1 x 2.20zł = 2.20zł
// SUMA: 32.99zł
// ```
