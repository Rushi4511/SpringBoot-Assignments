package Week1_Core_Concepts_Of_Spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "syrup.flavour",havingValue = "strawberry")
public class StrawberrySyrup implements Syrup{




    @Override
    public String getSyruptype() {
        return "StrawBerry Syrup";
    }
}
