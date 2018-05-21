package pl.coderslab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_01")
public class Servlet_01 extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String value = req.getParameter("value");
			String radio = req.getParameter("radio");
			double currencyExchangeRate = 0;
			if (radio.equals("eur_to_usd")) {
				currencyExchangeRate = 1.18;
			} else if (radio.equals("usd_to_eur")) {
				currencyExchangeRate = 0.85;
			} else if (radio.equals("eur_to_pln")) {
				currencyExchangeRate = 4.31;
			} else if (radio.equals("pln_to_eur")) {
				currencyExchangeRate = 0.23;
			} else if (radio.equals("usd_to_pln")) {
				currencyExchangeRate = 3.67;
			} else if (radio.equals("pln_to_usd")) {
				currencyExchangeRate = 0.27;
			}
			double currencyToConvert = Double.parseDouble(value);
			double result = currencyToConvert * currencyExchangeRate;
			String output = "<h1>" + "Wynik to: " + result + "</h1>";
			resp.getWriter().append(output);
		} catch (NumberFormatException | NullPointerException e) {
			resp.getWriter().append("<h1>" + "Brak danych" + "</h1>");
		}
	}
}

// #### Zadanie 1
//
// 1. W projekcie stwórz servlet `Servlet_01` oraz stronę HTML `index.html`,
// w której zawarty jest formularz, który zawiera jedno pole liczbowe oraz pola
// typu `radio`.
// Po przesłaniu formularza Servlet przelicza waluty:
// * `EUR → USD`
// * `USD → EUR`
// * `EUR → PLN`
// * `PLN → EUR`
// * `USD → PLN`
// * `PLN → USD`
//
// Przeliczona kwota pokazuje się jako wynik w przeglądarce.
// Hint: Kursy walut możesz przygotować jako tablicę (kursy mogą być przez
// Ciebie wymyślone lub możesz je na stronie [NBP][nbp]).