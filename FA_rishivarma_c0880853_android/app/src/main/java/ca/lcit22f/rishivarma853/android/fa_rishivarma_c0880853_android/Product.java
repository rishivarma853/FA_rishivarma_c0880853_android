package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

public class Product {
    int _id;
    String _name;
    String _description;
    int _price;

    public Product() {
    }

    public Product(int id, String name, String description, int price) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._price = price;
    }

    public Product(String name, String description) {
        this._name = name;
        this._description = description;
    }

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this._name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getDescription() {
        return this._description;
    }

    public void setDescription(String phone_number) {
        this._description = phone_number;
    }

    public int getPrice() {
        return this._price;
    }

    public void setPrice(int price) {
        this._price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", _name='" + _name + '\'' +
                ", _description='" + _description + '\'' +
                ", _price=" + _price +
                '}';
    }
}