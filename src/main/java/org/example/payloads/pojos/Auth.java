package org.example.payloads.pojos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Auth {


        @SerializedName("username")
        @Expose
        private String username;
        @SerializedName("password")
        @Expose
        private String password;

        public Auth(){
            System.out.println("Default Con - Auth");
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


}
