public class Manufacturer
{

    private String name;
    private int productsNumber;

    public Manufacturer(String name)
    {
        this.name = name;
        productsNumber = 1;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getProductsNumber()
    {
        return productsNumber;
    }

    public void addOneToProductsNumber()
    {
        this.productsNumber++;
    }
}
