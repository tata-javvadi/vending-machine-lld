package Inventory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {
    private final Map<Integer, Product> aisleToProductMap;
    private final Map<Integer, Integer> productIdToCountMap;
    private final Queue<Integer> availableAisles;

    public Inventory(int aisleCount) {
        if (aisleCount <= 0) {
            throw new IllegalArgumentException("Aisle count must be positive.");
        }
        availableAisles = new LinkedList<Integer>();
        for (int aisleNo = 1; aisleNo <= aisleCount; aisleNo++) {
            availableAisles.add(aisleNo);
        }
        aisleToProductMap = new HashMap<Integer, Product>();
        productIdToCountMap = new HashMap<Integer, Integer>();
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }

        int productId = product.getId();
        int productCount = productIdToCountMap.containsKey(productId)
                ? productIdToCountMap.get(productId) : 0;

        if (productCount == 0) {
            if (availableAisles.isEmpty()) {
                throw new IllegalStateException("Out of space to add product.");
            }
            int aisle = availableAisles.remove();
            aisleToProductMap.put(aisle, product);
        } else {
            // A product type already has an aisle; add another unit to its stock.
            boolean found = false;
            for (Product stored : aisleToProductMap.values()) {
                if (stored.getId() == productId) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new IllegalStateException("Inventory count is inconsistent.");
            }
        }
        productIdToCountMap.put(productId, productCount + 1);
    }

    public Product getProductAt(int aisleNumber) {
        return aisleToProductMap.get(aisleNumber);
    }

    public boolean checkIfProductAvailable(int productId) {
        Integer count = productIdToCountMap.get(productId);
        return count != null && count > 0;
    }

    public void deductProductCount(int aisleNumber) {
        Product product = aisleToProductMap.get(aisleNumber);
        if (product == null) {
            throw new IllegalStateException("No product in aisle " + aisleNumber + ".");
        }

        int productId = product.getId();
        Integer currentCount = productIdToCountMap.get(productId);
        if (currentCount == null || currentCount <= 0) {
            throw new IllegalStateException("Product is out of stock.");
        }

        int updatedCount = currentCount - 1;
        if (updatedCount == 0) {
            productIdToCountMap.remove(productId);
            aisleToProductMap.remove(aisleNumber);
            availableAisles.add(aisleNumber);
        } else {
            productIdToCountMap.put(productId, updatedCount);
        }
    }

    public int getStockCount(int productId) {
        Integer count = productIdToCountMap.get(productId);
        return count == null ? 0 : count;
    }

    public int getAvailableAisleCount() {
        return availableAisles.size();
    }
}
