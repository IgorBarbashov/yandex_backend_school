package product_report;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

class Filter {
    enum FILTER_FIELD_NAME {
        NAME_CONTAINS, PRICE_GREATER_THAN, PRICE_LESS_THAN, DATE_BEFORE, DATE_AFTER
    }

    public String getNameContainsFilter() {
        return nameContainsFilter;
    }

    public int getPriceGreaterThanFilter() {
        return priceGreaterThanFilter;
    }

    public int getPriceLessThanFilter() {
        return priceLessThanFilter;
    }

    public Date getDateBeforeFilter() {
        return dateBeforeFilter;
    }

    public Date getDateAfterFilter() {
        return dateAfterFilter;
    }

    String nameContainsFilter;
    int priceGreaterThanFilter;
    int priceLessThanFilter;
    Date dateBeforeFilter;
    Date dateAfterFilter;

    public void setNameContainsFilter(String nameContainsFilter) {
        this.nameContainsFilter = nameContainsFilter.toLowerCase();
    }

    public void setPriceGreaterThanFilter(int priceGreaterThanFilter) {
        this.priceGreaterThanFilter = priceGreaterThanFilter;
    }

    public void setPriceLessThanFilter(int priceLessThanFilter) {
        this.priceLessThanFilter = priceLessThanFilter;
    }

    public void setDateBeforeFilter(String dateBeforeFilter) throws java.text.ParseException {
        this.dateBeforeFilter = Solution.sdf.parse(dateBeforeFilter);
    }

    public void setDateAfterFilter(String dateAfterFilter) throws java.text.ParseException {
        this.dateAfterFilter = Solution.sdf.parse(dateAfterFilter);
    }
}

public class Solution {
    public static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public static void main(String[] args) throws java.text.ParseException {
        Filter filter = new Filter();
        JSONArray products = readData(filter);
        ArrayList<JSONObject> resultList = new ArrayList<>();

        for (Object product : products) {
            if (validateProductByFilter((JSONObject) product, filter)) {
                resultList.add((JSONObject) product);
            }
        }

        Collections.sort(resultList, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                return ((Long) o1.get("id")).intValue() - ((Long) o2.get("id")).intValue();
            }
        });

        System.out.println(resultList);
    }

    private static JSONArray readData(Filter filter) {
        try (BufferedReader r = new BufferedReader(new InputStreamReader(System.in))) {
            JSONParser parser = new JSONParser();
            String jsonString = "{\"products\":" + r.readLine() + "}";
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            for (int i = 0; i < 5; i++) {
                String[] cF = r.readLine().split(" ");
                String fN = cF[0];
                String fV = cF[1];

                if (fN.equals(Filter.FILTER_FIELD_NAME.NAME_CONTAINS.name())) {
                    filter.setNameContainsFilter(fV);
                } else if (fN.equals(Filter.FILTER_FIELD_NAME.PRICE_GREATER_THAN.name())) {
                    filter.setPriceGreaterThanFilter(Integer.parseInt(fV));
                } else if (fN.equals(Filter.FILTER_FIELD_NAME.PRICE_LESS_THAN.name())) {
                    filter.setPriceLessThanFilter(Integer.parseInt(fV));
                } else if (fN.equals(Filter.FILTER_FIELD_NAME.DATE_BEFORE.name())) {
                    filter.setDateBeforeFilter(fV);
                } else if (fN.equals(Filter.FILTER_FIELD_NAME.DATE_AFTER.name())) {
                    filter.setDateAfterFilter(fV);
                }
            }

            return (JSONArray) jsonObject.get("products");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean validateProductByFilter(JSONObject product, Filter filter) throws java.text.ParseException {
        String name = (String) product.get("name");
        int price = ((Long) product.get("price")).intValue();
        String dateString = (String) product.get("date");
        Date date = Solution.sdf.parse(dateString);

        if (
                !name.toLowerCase().contains(filter.getNameContainsFilter()) ||
                price > filter.getPriceLessThanFilter() ||
                price < filter.getPriceGreaterThanFilter() ||
                date.before(filter.getDateAfterFilter()) ||
                date.after(filter.getDateBeforeFilter())
        ) {
            return false;
        }

        return true;
    }
}