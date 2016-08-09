

./mvnw spring-boot:run


curl -X POST -H "Content-Type: application/json" -d '{"customer":"leandro"}' "http://localhost:8080/api/cart/new"

curl -X POST -H "Content-Type: application/json" -d '{ "customer":"leandro", "sku": "mbp" }' "http://localhost:8080/api/cart/add-item"
curl -X POST -H "Content-Type: application/json" -d '{ "customer":"leandro", "sku": "ipd" }' "http://localhost:8080/api/cart/add-item"
curl -X POST -H "Content-Type: application/json" -d '{ "customer":"leandro", "sku": "atv" }' "http://localhost:8080/api/cart/add-item"
curl -X POST -H "Content-Type: application/json" -d '{ "customer":"leandro", "sku": "vga" }' "http://localhost:8080/api/cart/add-item"
