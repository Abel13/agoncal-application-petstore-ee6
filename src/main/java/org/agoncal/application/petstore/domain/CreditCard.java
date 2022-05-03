package org.agoncal.application.petstore.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 *         --
 */

@Embeddable
@ToString(includeFieldNames = true)
public class CreditCard {

    // ======================================
    // =             Attributes             =
    // ======================================

    @Column(name = "credit_card_number", length = 30)
    @NotNull
    @Size(min = 1, max = 30)
    @Getter @Setter private String creditCardNumber;
    @Column(name = "credit_card_type")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter @Setter private CreditCardType creditCardType;
    @Column(name = "credit_card_expiry_date", length = 5)
    @NotNull
    @Size(min = 1, max = 5)
    @Getter @Setter private String creditCardExpDate;

    // ======================================
    // =            Constructors            =
    // ======================================

    public CreditCard() {
    }

    public CreditCard(String creditCardNumber, CreditCardType creditCardType, String creditCardExpDate) {
        this.creditCardNumber = creditCardNumber;
        this.creditCardType = creditCardType;
        this.creditCardExpDate = creditCardExpDate;
    }

    // ======================================
    // =   Methods hash, equals, toString   =
    // ======================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreditCard)) return false;

        CreditCard that = (CreditCard) o;

        if (!creditCardNumber.equals(that.creditCardNumber)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return creditCardNumber.hashCode();
    }
}