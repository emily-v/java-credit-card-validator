package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.CreditCard;
import service.ValidatorService;

public class ValidatorServlet extends HttpServlet {
	
	ValidatorService service = new ValidatorService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StringBuilder builder = new StringBuilder();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String json = builder.toString();
		ObjectMapper om = new ObjectMapper();
		CreditCard input = om.readValue(json, CreditCard.class);
		PrintWriter out = response.getWriter();
		CreditCard card = service.validateCard(input);
		if (service.checkBlacklisted(input.getNumber()) == true) {
			out.print("This card is blacklisted!");
			return;
		}
		if(card != null) {
				out.print(om.writeValueAsString(card));
		} else {
			out.print("Card Number or Expiration Date is not valid");
		}
		out.flush();
	}
}
