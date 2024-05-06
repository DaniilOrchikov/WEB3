package com.example.web3;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.text.DecimalFormat;

@Named
@ApplicationScoped
public class Converter {
    public String convertDoubleToString(double value) {
        BigDecimal number = new BigDecimal(value);
        return number.stripTrailingZeros().toString();
    }
    public String convertDoubleToString(double value, Long numberOfDecimalPlaces) {
        StringBuilder format = new StringBuilder("0.");
        for (int i = 0; i < numberOfDecimalPlaces.intValue(); i++) {
            format.append("0");
        }
        DecimalFormat df = new DecimalFormat(format.toString());
        return df.format(value);
    }
}
