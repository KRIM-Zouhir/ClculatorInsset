package org.insset.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.insset.client.service.PourcentageService;

/**
 * The server-side implementation of the percentage discount calculation service.
 */
@SuppressWarnings("serial")
public class PourcentageServiceImpl extends RemoteServiceServlet implements PourcentageService {

    @Override
    public double calculateDiscount(double montant, double pourcentage) throws IllegalArgumentException {
        if (montant < 0 || pourcentage < 0) {
            throw new IllegalArgumentException("Les valeurs ne peuvent pas être négatives.");
        }

        double remise = (pourcentage / 100) * montant;
        return montant - remise;  // Return the final price after discount
    }

}
