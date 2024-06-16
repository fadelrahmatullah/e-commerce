# E-Commerce

# ======= Authorization ==================

## Mendapatkan Token
Untuk mendapatkan token, Anda perlu melakukan permintaan POST ke endpoint token OAuth2. Gunakan URL berikut:
POST http://localhost:9000/oauth2/token

#### Headers
- **Authorization**: Basic Auth
  - Username: `admin-client`
  - Password: `secret`

#### Body
Gunakan `application/x-www-form-urlencoded` untuk mengirimkan data berikut:
- `grant_type`: `client_credentials`

### Contoh Permintaan Menggunakan cURL

# USE CURL sh
curl --location --request POST 'http://localhost:9000/oauth2/token' \
--header 'Authorization: Basic YWRtaW4tY2xpZW50OnNlY3JldA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \

### Contoh Respons
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "bearer",
  "expires_in": 3600,
}


# ========== Service Inventory ==================
# ADD NEW PRODUCT
    POST ke endpoint : localhost:9003/api/inventory/v1/product/add

    # Headers
    Content-Type: application/json
    Authorization: Bearer token yang diperoleh dari langkah Mendapatkan Token di atas.

    # Body
    Gunakan application/json untuk mengirimkan data produk berikut:
    productName: Nama produk, misalnya "TEST".
    productPrice: Harga produk, misalnya 28000.
    productQty: Jumlah produk, misalnya 10.

    # CURL
    curl --location 'http://localhost:9003/api/inventory/v1/product/add' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer <access_token>' \
    --data '{
    "productName": "ROKOK",
    "productPrice": 28000,
    "productQty": 10
    }'
    
# UPDATE PRODUCT
    PUT ke endpoint : PUT http://localhost:9003/api/inventory/v1/product/update/{productId}
    NOTE : Gantilah {productId} dengan ID produk yang ingin diperbarui.

    # Headers
    Content-Type: application/json
    Authorization: Bearer token yang diperoleh dari langkah Mendapatkan Token di atas.
    
    # Body
    Gunakan application/json untuk mengirimkan data produk yang diperbarui:
    productName: Nama baru produk, misalnya "ROKOK FILTER".
    productPrice: Harga baru produk, misalnya 28000.0.
    productQty: Jumlah baru produk, misalnya 10.

    # CURL
    curl --location --request PUT 'http://localhost:9003/api/inventory/v1/product/update/1' \
    --header 'Content-Type: application/json' \
    --header 'Authorization: Bearer <access_token>' \
    --data '{
        "productName": "ROKOK FILTER",
        "productPrice": 28000.0,
        "productQty": 10
    }'

# SEARCH PRODUCT
    GET ke endpoint : GET http://localhost:9003/api/inventory/v1/inventory/search

    # Query Parameters
    productName: Nama produk yang dicari. Kosongkan jika ingin mencari semua produk.
    pageNo: Nomor halaman untuk hasil pencarian (default: 1).
    pageSize: Jumlah produk per halaman (default: 10).
    
    # Headers
    Content-Type: application/json
    Authorization: Bearer token yang diperoleh dari langkah Mendapatkan Token di atas.

    # CURL
    curl --location 'http://localhost:9003/api/inventory/v1/inventory/search?productName=&pageNo=1&pageSize=5' \
    --header 'Authorization: Bearer <access_token>'

# ========== Service ORDER ==================

    # Order Product
    POST ke endpoint : http://localhost:9002/api/order/v1/order

    ### Request Body
    Request body harus berupa JSON yang memuat informasi pesanan:
    ```json
    {
    "productId": 1,
    "quantity": 10
    }

    ### CONTOH CURL
    curl --location --request POST 'http://localhost:9002/api/order/v1/order' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "productId": 1,
        "quantity": 10
    }'
