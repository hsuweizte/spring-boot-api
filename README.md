## REST服務設計



## 1、URL請求

```
GET http://localhost:8080/books  讀書單列表
```

```
GET http://localhost:8080/books/{id} 獲取一條書單
```

```
POST http://localhost:8080/books 新增一條書單
```

```
PUT http://localhost:8080/books/{id} 更新一條書單
```

```
DELETE http://localhost:8080/books/{id} 刪除一條書單
```

```
DELETE http://localhost:8080/books 刪除所有書單
```



## 2、Response

單個物件：

```json
{
  "field1": "value",
  "field2": true,
  "field3": []
}
```

集合：

```json
[
  {
    "field1": "value",
    "field2": true,
    "field3": []
  },
  {
    "field1": "another value",
    "field2": false,
    "field3": []
  }
]
```



## 3、Eroors

**400 series errors：**

```json
{
   "message": "Not Found"
}
```

**500系列（500，501，502...）不返回JSON資料，只返回狀態**



**validation Errors：**

```json
{
  "message": "Validation Failed",
  "errors": [
    {
      "message": "Field is not valid"
    },
    {
      "message": "OtherField is already used"
    }
  ]
}
```



## 4、HTTP Status Codes

Success codes:

-  `200 OK` - Request succeeded. Response included
-  `201 Created` - Resource created. URL to new resource in Location header
-  `204 No Content` - Request succeeded, but no response body

Error codes:

-  `400 Bad Request` - Could not parse request
-  `401 Unauthorized` - No authentication credentials provided or authentication failed
-  `403 Forbidden` - Authenticated user does not have access
-  `404 Not Found` - Resource not found
-  `415 Unsupported Media Type` - POST/PUT/PATCH request occurred without a `application/json` content type
-  `422 Unprocessable Entry` - A request to modify or create a resource failed due to a [validation error](http://dev.enchant.com/api/v1#validation)
-  `429 Too Many Requests` - Request rejected due to [rate limiting](http://dev.enchant.com/api/v1#ratelimit)
-  `500, 501, 502, 503, etc` - An internal server error occured

