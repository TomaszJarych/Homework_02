package pl.coderslab;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Servlet_02")
public class Servlet_02 extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String binary = req.getParameter("binary");
		char[] binaryArray = binary.toCharArray();
		String output = "";
		int pow = 0;
		int result=0;
		if (binaryCheck(binary)) {
		for (int i = binaryArray.length-1; i >= 0; i--) {
			int number = Character.getNumericValue(binaryArray[i]);
			result += number * Math.pow(2, pow);
			pow++;
			
		}	
		output += "Wynik to " + result;
		} else {
			output += "Ciąg zawiera niedozwolone znaki";
		}
		resp.getWriter().append("<h1>").append(output).append("</h1>");
	}

	public boolean binaryCheck(String binary) {
		String output = "";
		char[] binaryArray = binary.toCharArray();
		for (char c : binaryArray) {
			if (c == '0' || c == '1') {
				output += "";
			} else {
				output += "zawiera inne znaki";
			}
		}
		if (output.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
// #### Zadanie 2
//
// W projekcie stwórz servlet `Servlet_02`, oraz stronę HTML `index.html`, w
// której zawarty jest formularz, zawierający jedno pole liczbowe. Po przesłaniu
// formularza Servlet przelicza wpisaną do formularza wartość binarną liczby na
// wartość dziesiętną.
// Servlet musi wykonać następujące kroki:
// * Sprawdzić czy przekazany ciąg zawiera **tylko** `0` i `1`, jeśli nie,
// wyświetlić stosowny komunikat
// * Podzielić ciąg na pojedyncze znaki
// * Idąc od końca ciągu mnożyć kolejne liczby przez kolejne potęgi liczby `2`
// * `Ostatnia liczba` x `2^0`
// * `Przedostatnia liczba` x `2^1`
// * itd.
// * Po dokonaniu obliczeń należy zsumować wszystkie wyniki potęgowania.
// * Po dokonaniu obliczenia wyświetl na stronie komunikat np. `1001 to liczba
// 9`.
//
//
// Hint: Jeżeli nie rozumiesz jak przeliczyć liczbę zapisaną w systemie binarnym
// na system dziesiętny to zajrzyj [tutaj][binary-convertion].