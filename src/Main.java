import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main
{

    static int LINE_LENGTH = 40;
    static String headline = " nazwa producenta; przekątna ekranu; rozdzielczość ekranu; rodzaj powierzchni ekranu; czy ekran jest dotykowy; nazwa procesora; liczba rdzeni fizycznych; prędkość taktowania MHz; wielkość pamięci RAM; pojemność dysku; rodzaj dysku; nazwa układu graficznego; pamięć układu graficznego; nazwa systemu operacyjnego; rodzaj napędu fizycznego w komputerze";

    public static void main(String[] args)
    {

        FileReader fileReader = null;
        String filePath = "D:/Semestr 3/IS/zad01/t1_katalog.txt";
        ArrayList<Manufacturer> manufacturerList = new ArrayList<Manufacturer>();

        //Read file
        try
        {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e)
        {
            System.out.println("Error, file could not be opened\n" + e.getMessage());
        }

        BufferedReader bufferedReader = new BufferedReader(fileReader);

        PrintHeadline();
        PrintFile(manufacturerList, bufferedReader);
        PrintManufacturersLaptops(manufacturerList);
    }


    public static void PrintManufacturersLaptops(List<Manufacturer> manufacturerList)
    {
        System.out.println("\nNumber of laptops by manufacturer");
        for (Manufacturer m : manufacturerList)
        {
            String mName = m.getName();
            System.out.print(mName + ": ");
            for (int i = mName.length(); i < 10; i++)
            {
                System.out.print(" ");
            }
            System.out.println(m.getProductsNumber());
        }
    }

    public static void PrintFile(List<Manufacturer> manufacturerList, BufferedReader bufferedReader)
    {
        String line;
        int rowNumber = 1;
        int colNumber = 1;
        String manufacturerName = "";


        try
        {
            while ((line = bufferedReader.readLine()) != null)
            {
                if (rowNumber < 10)
                {
                    System.out.print("0");
                }
                System.out.print(rowNumber + "  |");

                StringTokenizer stringTokenizer = new StringTokenizer(line, ";", true);
                String lastItem = "";
                while (stringTokenizer.hasMoreTokens())
                {
                    String item = stringTokenizer.nextToken();
                    if (colNumber == 1)
                    {
                        manufacturerName = item;
                    }

                    if (lastItem.equals(";") && item.equals(";"))
                    {
                        System.out.print(" ");
                        System.out.print("-");
                        for (int i = item.length(); i < LINE_LENGTH; i++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                    } else if (item.equals(";"))
                    {

                    } else
                    {
                        System.out.print(" ");
                        System.out.print(item);
                        for (int i = item.length(); i < LINE_LENGTH; i++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                    }

                    colNumber++;
                    lastItem = item;
                }

                System.out.println();
                rowNumber++;
                colNumber = 1;

                boolean isNewManufacturer = true;
                for (Manufacturer m : manufacturerList)
                {
                    if (m.getName().equals(manufacturerName))
                    {
                        isNewManufacturer = false;
                        m.addOneToProductsNumber();
                    }
                }
                if (isNewManufacturer)
                {
                    Manufacturer manufacturer = new Manufacturer(manufacturerName);
                    manufacturerList.add(manufacturer);
                }

            }

        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void PrintHeadline()
    {
        System.out.println("\nZadanie 1\n");

        StringTokenizer headlineStringTokenizer = new StringTokenizer(headline, ";");
        System.out.print("No.  ");
        while (headlineStringTokenizer.hasMoreTokens())
        {
            String item = headlineStringTokenizer.nextToken();
            System.out.print(item);
            for (int i = item.length(); i <= LINE_LENGTH; i++)
            {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.println();
        for (int i = 0; i < 16 * LINE_LENGTH; i++)
        {
            System.out.print("-");
        }
        System.out.println();
    }


}