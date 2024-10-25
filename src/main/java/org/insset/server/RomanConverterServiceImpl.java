package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.RomanConverterService;

@SuppressWarnings("serial")
public class RomanConverterServiceImpl extends RemoteServiceServlet implements RomanConverterService {

    @Override
    public String convertDateYears(String date) throws IllegalArgumentException {
        // Convertir la date au format XX/XX/XXXX en romain
        String[] parts = date.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Format de date incorrect");
    }

        String jour = ConvertirDecimalEnRomain.convertir(Integer.parseInt(parts[0]));
        String mois = ConvertirDecimalEnRomain.convertir(Integer.parseInt(parts[1]));
        String annee = ConvertirDecimalEnRomain.convertir(Integer.parseInt(parts[2]));

        return jour + "/" + mois + "/" + annee;
    }

    @Override
    public Integer convertRomanToArabe(String nbr) throws IllegalArgumentException {
        // Implémentez votre code
        return 3;
        }

    @Override
    public String convertArabeToRoman(Integer nbr) throws IllegalArgumentException {
        if (nbr < 1 || nbr > 3999) {
            throw new IllegalArgumentException("Le nombre doit être entre 1 et 3999.");
        }
        // Appelle la méthode de conversion
        return ConvertirDecimalEnRomain.convertir(nbr);
    }

    // Classe pour la conversion des décimaux en chiffres romains
    public static class ConvertirDecimalEnRomain {
        
        public static String convertir(int nombre) {
            String[] milliers = {"", "M", "MM", "MMM"};
            String[] centaines = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String[] dizaines = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String[] unites = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            StringBuilder result = new StringBuilder();
            
            result.append(milliers[nombre / 1000]);
            nombre %= 1000;
            result.append(centaines[nombre / 100]);
            nombre %= 100;
            result.append(dizaines[nombre / 10]);
            nombre %= 10;
            result.append(unites[nombre]);

            return result.toString();
        }
    }
    }
