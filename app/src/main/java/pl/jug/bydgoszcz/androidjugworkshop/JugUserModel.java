package pl.jug.bydgoszcz.androidjugworkshop;

public class JugUserModel {
    private long id;
    private String name;
    private String username;
    private String email;
    private Address address;

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public long getId() {
        return id;
    }

    public String imageUrl() {
        return "http://www.dfhtechnologies.com/images/user.png";
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }

    public static class Address {
        private String street;
        private String suite;
        private String city;
        private String zipcode;

        public String getStreet() {
            return street;
        }

        public String getSuite() {
            return suite;
        }

        public String getCity() {
            return city;
        }

        public String getZipcode() {
            return zipcode;
        }
    }
}