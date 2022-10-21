import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/*
    Login class is connected with facade as a dependency
 */
public class Login {

    /*
    This method validates the login credentials provided by the user
    with the text files provided ,ie, BuyerInfo.txt and SellerInfo.txt
    returns Usertype : 0 for buyer, 1 for seller
    -1 if invalid login
     */
    int login(String username, String password) {
        int userType = -1;

        HashMap<String, String> buyerCredentials = new HashMap<String, String>();
        HashMap<String, String> sellerCredentials = new HashMap<String, String>();


        try {
            File buyerFile = new File(
                    "BuyerInfo.txt");
            BufferedReader br
                    = new BufferedReader(new FileReader(buyerFile));


            String st;

            while ((st = br.readLine()) != null) {
                String[] splitArr = st.split(":");
                buyerCredentials.put(splitArr[0], splitArr[1]);
            }

            File sellerFile = new File("SellerInfo.txt");
            br
                    = new BufferedReader(new FileReader(sellerFile));

            while ((st = br.readLine()) != null) {
                String[] splitArr = st.split(":");
                sellerCredentials.put(splitArr[0], splitArr[1]);
            }

            //set user type
            if(buyerCredentials.containsKey(username) && buyerCredentials.get(username).equals(password)) {
                userType = 0;

            } else if(sellerCredentials.containsKey(username) && sellerCredentials.get(username).equals(password)) {
                userType = 1;
            }

        }catch (Exception e) {
            System.err.println("Error reading text files" + e.getMessage());
        }

        return userType;
    }

}
