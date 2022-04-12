package SpaceTraderApp.Connections;
import SpaceTraderApp.Data.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Online implements Modes {
    private static final String APIUrl = "https://api.spacetraders.io";
    private static final Gson gson = new Gson();
    private static Online online;

    private Online(){}

    public static Online getInstance() {
        if (online == null) {
            online = new Online();
        }
        return online;
    }


    @Override
    public User getUser(String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(APIUrl + "/my/account" + "?token=" + token);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    JSONObject jsonObject = new JSONObject(result);
                    try {
                        JSONObject jsonUser = (JSONObject) jsonObject.get("user");
                        User user = gson.fromJson(String.valueOf(jsonUser), User.class);
                        System.out.println(user.getCredits() + user.getUsername());
                        return user;
                    }
                    catch (Exception e){
                        return null;
                    }
                }
            } catch(ParseException exception) {
                System.out.println("Invalid input");
            }
        }
        return null;
    }

    @Override
    public GameStatus gameStatus() throws IOException {
        try {
            HttpRequest request = HttpRequest.newBuilder(new URI(
                    APIUrl + "/game/status"
            )).GET().build();

            HttpClient client = HttpClient.newBuilder().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String result = response.body();

            GameStatus gameStatus = gson.fromJson(result, GameStatus.class);
            System.out.println(gameStatus.getStatus());
            return gameStatus;

        } catch (URISyntaxException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserData createUser(String username) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/users/" + username + "/claim");
            post.setEntity(new StringEntity(""));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return gson.fromJson(result, UserData.class);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Loans getLoans(String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/types/loans" + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    Loans loans = gson.fromJson(result, Loans.class);

                    return loans;

                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }
        return null;
    }

    @Override
    public Loans takeOutLoan(String type, String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/my/loans" + "?token=" + token);
            post.setHeader("Content-type", "application/json");

            StringBuilder json = new StringBuilder();
            json.append("{");
            json.append("\"type\":\"").append(type).append("\"");
            json.append("}");

            post.setEntity(new StringEntity(json.toString()));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                return gson.fromJson(result, Loans.class);
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Loans getMyLoans(String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/my/loans/" + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    Loans loans = gson.fromJson(result, Loans.class);
                    return loans;

                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }
        return null;
    }

    @Override
    public Ships getAvailableShips(String token, String systemSymbol) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/systems/" + systemSymbol + "/ship-listings/"
                    + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray ships = jsonObject.getJSONArray("shipListings");

                    List<Ship> shipList = gson.fromJson(ships.toString(),
                            new TypeToken<List<Ship>>() {
                            }.getType());

                    Ships shipListings = new Ships();
                    shipListings.setShips(shipList);
                    System.out.println(shipListings.getShips().get(0).getSpeed());

                    return shipListings;

                }
            } catch (Exception e) {
                System.out.println("Something goes Wrong");
            }
        }
        return null;
    }

    @Override
    public Ships buyShip(String token, String location, String type) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/my" + "/ships" + "?token=" + token);
            post.setHeader("Content-type", "application/json");

            Map<String, String> map = new HashMap<>();
            map.put("location", location);
            map.put("type", type);

            post.setEntity(new StringEntity(gson.toJson(map)));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                Ships ships = gson.fromJson(result, Ships.class);
                System.out.println(ships.getShip().getId());
                return ships;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Ships getMyShips(String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/my/ships/" + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    Ships ships = gson.fromJson(result, Ships.class);


                    return ships;

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public PurchaseInfo purchaseFuel(String shipId, String good, int quantity, String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/my/purchase-orders" + "?token=" + token);
            post.setHeader("Content-type", "application/json");

            Map<String, Object> map = new HashMap<>();
            map.put("shipId", shipId);
            map.put("good", good);
            map.put("quantity", quantity);

            post.setEntity(new StringEntity(gson.toJson(map)));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                PurchaseInfo purchaseInfo = gson.fromJson(result, PurchaseInfo.class);
                System.out.println(purchaseInfo.getShip().getCargo().get(0).getGood());
                return purchaseInfo;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public MarketPlaces getMarketInfo(String location, String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/locations/" + location + "/marketplace" + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray places = jsonObject.getJSONArray("marketplace");

                    List<Marketplace> marketplaces = gson.fromJson(places.toString(),
                            new TypeToken<List<Marketplace>>() {
                            }.getType());

                    MarketPlaces marketPlaces = new MarketPlaces();
                    marketPlaces.setMarketplaces(marketplaces);
                    System.out.println(marketPlaces.getMarketplaces().get(0).getSymbol());

                    return marketPlaces;

                }
            } catch (Exception e) {
                System.out.println("invalid input");
            }
        }
        return null;
    }

    @Override
    public Locations getNearByLocation(String systemSymbol, String token) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(APIUrl + "/systems/" + systemSymbol + "/locations" + "?token=" + token);

            try (CloseableHttpResponse response = httpClient.execute(get)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);

                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray places = jsonObject.getJSONArray("locations");

                    List<Location> locationList = gson.fromJson(places.toString(),
                            new TypeToken<List<Location>>() {
                            }.getType());

                    Locations locations = new Locations();
                    locations.setLocationList(locationList);
                    System.out.println(locations.getLocationList().get(0).getTraits()[0]);

                    return locations;

                }
            } catch (Exception e) {
                System.out.println("Invalid parameters");
            }
        }
        return null;
    }

    @Override
    public FlightPlans createFlightPlan(String shipId, String destId, String token) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/my/flight-plans" +"?token=" + token);
            post.setHeader("Content-type", "application/json");

            Map<String, Object> map = new HashMap<>();
            map.put("shipId", shipId);
            map.put("destination", destId);

            post.setEntity(new StringEntity(gson.toJson(map)));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                FlightPlans flightPlans = gson.fromJson(result, FlightPlans.class);
                System.out.println(flightPlans.getFlightPlan().getId());
                return flightPlans;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public FlightPlans getCurFlightPlan(String flightId, String token) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(APIUrl + "/my/flight-plans/" +
                    flightId + "?token=" + token);
            try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                    FlightPlans flightPlans = gson.fromJson(result, FlightPlans.class);
                    System.out.println(flightPlans.getFlightPlan().getId());
                    return flightPlans;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public PurchaseInfo sellGood(String shipId, String good, int quantity,String token) throws IOException {
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(APIUrl + "/my/sell-orders" +"?token=" + token);
            post.setHeader("Content-type", "application/json");

            Map<String, Object> map = new HashMap<>();
            map.put("shipId", shipId);
            map.put("good", good);
            map.put("quantity", quantity);

            post.setEntity(new StringEntity(gson.toJson(map)));

            CloseableHttpResponse response = httpClient.execute(post);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
                PurchaseInfo purchaseGood = gson.fromJson(result, PurchaseInfo.class);
                System.out.println(purchaseGood.getCredits());
                return purchaseGood;
            }

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
