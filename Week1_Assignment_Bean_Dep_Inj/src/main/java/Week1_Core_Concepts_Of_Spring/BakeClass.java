package Week1_Core_Concepts_Of_Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BakeClass {

    @Autowired
    Frosting frosting;

    @Autowired
    Syrup syrup;

    void BakingCake(){
        System.out.println("Syrub Type: "+ syrup.getSyruptype());
        System.out.println("Frosting type: "+frosting.getFrostingType());
    }
}
