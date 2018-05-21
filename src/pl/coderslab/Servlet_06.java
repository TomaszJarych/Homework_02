package pl.coderslab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_06")
public class Servlet_06 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			double num1 = Double.parseDouble(req.getParameter("number1"));
			double num2 = Double.parseDouble(req.getParameter("number2"));
			double num3 = Double.parseDouble(req.getParameter("number3"));
			double num4 = Double.parseDouble(req.getParameter("number4"));
			double sum = num1 + num2 + num3+num4;
			double average = (num1 + num2 + num3+num4) / 4;
			double ratio = num1 * num2 * num3*num4;
			String result = "<h1><div>" + "Liczby to: " + "<div>" + num1 + "</div>" + "<div>" + num2 + "</div>"
					+ "<div>" + num3 + "</div>" + "<div>" + num4 + "</div>" + "<div>" + "Suma to: " + sum + "</div>"
					+ "<div>" + "Srednia to: " + average + "</div>"+"Iloczyn to: "+ratio+"</div></h1>";
			resp.getWriter().append(result);

		} catch (NumberFormatException e) {
			resp.getWriter().append("<h1>Bledne dane!</h1>");
		}catch (NullPointerException e) {
			resp.getWriter().append("<h1>Brak danych!</h1>");
		}
	}
}
// #### Zadanie 6
//
// W projekcie stwórz servlet `Servlet_06`, oraz stronę HTML `index.html`, w
// której zawarty jest formularz przesyłający (metodą GET) 4 parametry liczbowe
// o nazwie `num`. Po przejściu do servletu oblicz ich średnią, sumę oraz
// iloczyn i zwróć wynik w przeglądarce:
//
// ````
// Liczby:
// - x1
// - x2
// - x3
// - x4
// Średnia:
// - średnia
// Suma:
// - suma
// Iloczyn:
// - iloczyn
// ````