package pl.coderslab;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_04")
public class Servlet_04 extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		String output = "";
		if (Objects.isNull(cookies)) {
			Cookie cookie = new Cookie("visit", 1 + "");
			cookie.setMaxAge(60 * 60 * 24 * 365);
			resp.addCookie(cookie);
			output = "Witaj pierwszy raz na naszej stronie";
		} else {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("visit")) {
					int number = Integer.parseInt(cookie.getValue())+1;
					output = "Witaj, odwiedziłeś nas już " +number+" razy";
					cookie.setValue(number+"");
					resp.addCookie(cookie);
				}
			}

		}
		resp.getWriter().append("<h1>").append(output).append("</h1>");
	}
}
// #### Zadanie 4
// W projekcie stwórz servlet `Servlet_04`. Celem zadania jest wyświetlanie
// ilości wizyt na stronie. W servlecie:
// 1. Sprawdź czy użytkownik posiada zapisane ciasteczko o nazwie `visits`:
// * jeśli nie, wyświetl komunikat: `Witaj pierwszy raz na naszej stronie` oraz
// dodaj ciasteczko o nazwie `visit`s zapisując mu wartość 1 i czas ważności 1
// rok.
// * jeśli ciasteczko jest zapisane, pobierz jego aktualną wartość i wypisz na
// stronie komunikat `Witaj, odwiedziłeś nas już X razy`. Zwiększ też wartość
// ciasteczka o 1.