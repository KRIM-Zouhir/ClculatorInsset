package org.insset.client.calculator.pourcentage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ResetButton;
import com.google.gwt.user.client.ui.SubmitButton;
import com.google.gwt.user.client.ui.TextBox;
import org.insset.client.message.dialogbox.DialogBoxInssetPresenter;
import org.insset.client.service.PourcentageService;
import org.insset.client.service.PourcentageServiceAsync;

public class CalculatorPourcentageRemise extends Composite {

    private final PourcentageServiceAsync pourcentageService = GWT.create(PourcentageService.class);

    @UiField
    public ResetButton boutonClear;
    @UiField
    public SubmitButton boutonCalculate;
    @UiField
    public TextBox montantDepart;
    @UiField
    public TextBox pourcentageRemise;
    @UiField
    public Label errorLabel;
    @UiField
    public Label resultLabel;

    interface MainUiBinder extends UiBinder<HTMLPanel, CalculatorPourcentageRemise> {
    }

    private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);

    public CalculatorPourcentageRemise() {
        initWidget(ourUiBinder.createAndBindUi(this));
        initHandler();
    }

    private void initHandler() {
        boutonClear.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                montantDepart.setText("");
                pourcentageRemise.setText("");
                errorLabel.setText("");
                resultLabel.setText("");
            }
        });
        boutonCalculate.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                calculateDiscount();
            }
        });
    }

    private void calculateDiscount() {
        final double montant;
        final double pourcentage;

        try {
            montant = Double.parseDouble(montantDepart.getText());
            pourcentage = Double.parseDouble(pourcentageRemise.getText());
        } catch (NumberFormatException e) {
            errorLabel.addStyleName("serverResponseLabelError");
            errorLabel.setText("Format incorrect");
            return;
        }

        if (montant < 0 || pourcentage < 0) {
            errorLabel.addStyleName("serverResponseLabelError");
            errorLabel.setText("Les valeurs ne peuvent pas être négatives");
            return;
        }

        // Asynchronous call to the server-side method
        pourcentageService.calculateDiscount(montant, pourcentage, new AsyncCallback<Double>() {
            @Override
            public void onFailure(Throwable caught) {
                errorLabel.addStyleName("serverResponseLabelError");
                errorLabel.setText("Erreur lors de la connexion au service.");
            }

            @Override
            public void onSuccess(Double result) {
                resultLabel.setText("Prix final : " + result);
                new DialogBoxInssetPresenter("Calcul de la remise", "Montant de départ : " + montant + "\nPourcentage de remise : " + pourcentage + "\nPrix final : " + result);
            }
        });
    }
}
