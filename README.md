
## How to build a container image

```
./mvnw -Pnative spring-boot:build-image \
  -DskipTests -Dspring-boot.build-image.imageName=ghcr.io/making/hello-native-world:0.0.1-snapshot

docker push ghcr.io/making/hello-native-world:0.0.1-snapshot
```

## How to Deploy to Azure Container Apps

```
az group create --resource-group hello-native-world-rg --location japaneast
```

```
az containerapp env create \
  --name hello-native-world-env \
  --resource-group hello-native-world-rg \
  --location japaneast \
  --logs-destination none
```

```
az containerapp create \
  --name hello-native-world \
  --resource-group hello-native-world-rg \
  --environment hello-native-world-env \
  --image ghcr.io/making/hello-native-world:0.0.1-snapshot \
  --target-port 8080 \
  --ingress external \
  --memory 0.5Gi \
  --cpu 0.25 \
  --min-replicas 0 \
  --max-replicas 1
```

```
az containerapp show -n hello-native-world -g hello-native-world-rg -o table
az containerapp logs show -n hello-native-world -g hello-native-world-rg --tail 100
```