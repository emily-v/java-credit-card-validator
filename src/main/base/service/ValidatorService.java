package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.CreditCard;

public class ValidatorService {
	
	public CreditCard validateCard(CreditCard card) {
		if (card == null) {
			return null;
		} else {
			boolean numberValid = isNumberValid(card.getNumber());
			boolean expValid = isExpValid(card.getExpiration());
			if (numberValid == true && expValid == true) {
				return card;
			} else {
				return null;
			}
		}
	}
	
	public boolean isNumberValid(String num) {
		String noSpaces = num.replaceAll("\\s", "");
		if (noSpaces.length() != 16) {
			return false;
		}
		if (!(noSpaces.charAt(0) == '4' || noSpaces.charAt(0) == '5')) {
			return false;
		}
		if (noSpaces.charAt(0) == '5') {
			if (Character.getNumericValue(noSpaces.charAt(1)) < 1 || Character.getNumericValue(noSpaces.charAt(1)) > 5) {
				return false;
			}
		}
		return luhnFormula(noSpaces);
	}
	
	public boolean checkBlacklisted(String num) {
		String noSpaces = num.replaceAll("\\s", "");
		ArrayList<String> blacklist = new ArrayList<String>();
		blacklist.add("4788384538552446");
		blacklist.add("5144385438523845");
		for (String item : blacklist) {
			if (noSpaces.equals(item)) return true;
		}
		return false;
	}
	
	public boolean luhnFormula(String num) {
	    int sum = 0;
	    boolean second = false;
	    for (int i = num.length() - 1; i >= 0; i--) {
	    	int n = Integer.parseInt(num.substring(i, i + 1));
	            if (second) {
	                n *= 2;
                    if (n > 9) {
                        n = (n % 10) + 1;
                    }
	            }
	            sum += n;
	            second = !second;
	    }
	    return (sum % 10 == 0);
    }
	
	public boolean isExpValid(String exp) {
		if (exp.length() != 5) return false;
		try {
			Date date = new SimpleDateFormat("MM/yy").parse(exp);
			Date now = new Date();
			if(date.after(now)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			System.out.println("Invalid Date Format");
			return false;
		}
	}
}
