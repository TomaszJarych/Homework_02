package pl.coderslab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Servlet_03")
public class Servlet_03 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String htmlStart = "<html lang=\"pl-PL\"><head><title>Servlet_03</title></head><body><form action=\"http://localhost:8080/Homework_02/Servlet_03\" method=\"post\">";
		String htmlEnd = "<input type=\"submit\" value=\"Wyslij\"></form></body></html>";
		String output = "";
		HttpSession httpSession = req.getSession();
		if (httpSession.isNew()) {
			for (int i = 1; i <= 5; i++) {
				String form = "<h1><label for=\"text" + i + "\">Wpisz text</label><input type=\"text\" name=\"text" + i
						+ "\" id=\"text" + i + "\"></h1>";
				output += form;
			}

		} else {
			String text1 = (String) httpSession.getAttribute("text1");
			String text2 = (String) httpSession.getAttribute("text2");
			String text3 = (String) httpSession.getAttribute("text3");
			String text4 = (String) httpSession.getAttribute("text4");
			String text5 = (String) httpSession.getAttribute("text5");
			String form1 = "<h1><label for=\"text\">Wpisz text</label><input type=\"text\" name=\"text1\" id=\"text\" value=\""
					+ text1 + "\"></h1>";
			String form2 = "<h1><label for=\"text\">Wpisz text</label><input type=\"text\" name=\"text2\" id=\"text\" value=\""
					+ text2 + "\"></h1>";
			String form3 = "<h1><label for=\"text\">Wpisz text</label><input type=\"text\" name=\"text3\" id=\"text\" value=\""
					+ text3 + "\"></h1>";
			String form4 = "<h1><label for=\"text\">Wpisz text</label><input type=\"text\" name=\"text4\" id=\"text\" value=\""
					+ text4 + "\"></h1>";
			String form5 = "<h1><label for=\"text\">Wpisz text</label><input type=\"text\" name=\"text5\" id=\"text\" value=\""
					+ text5 + "\"></h1>"; // gdy dodany zostanie do formularza
											// wpis "readonly" - pola nie będzie
											// można edytować
			output = form1 + form2 + form3 + form4 + form5;

		}
		resp.getWriter().append(htmlStart).append(output).append(htmlEnd);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String text1 = req.getParameter("text1");
		String text2 = req.getParameter("text2");
		String text3 = req.getParameter("text3");
		String text4 = req.getParameter("text4");
		String text5 = req.getParameter("text5");
		HttpSession httpSession = req.getSession();
		httpSession.setAttribute("text1", text1);
		httpSession.setAttribute("text2", text2);
		httpSession.setAttribute("text3", text3);
		httpSession.setAttribute("text4", text4);
		httpSession.setAttribute("text5", text5);
		resp.getWriter().append("<h1>").append("Dane wyslano").append("</h1>");
		doGet(req, resp);
	}
}
// #### Zadanie 3
// W projekcie stwórz servlet `Servlet_03`. Ma on implementować następujące
// funkcjonalności:
// 1. Po wejściu na stronę metodą GET (czyli w metodzie `doGet`), wygeneruj
// formularz z `5` polami tekstowymi `input`. Formularz ma przesyłać dane z
// użyciem `POST` do adresu z servletu.
// 2. Po otrzymaniu danych z formularza, zapisz pobrane dane w sesji.
// 3. Do metody `doGet` dopisz funkcjonalność sprawdzającą czy w sesji istnieją
// dane z poprzedniego punktu. Jeżeli dane istnieją wypełnij nimi pola `input`
// formularza.
