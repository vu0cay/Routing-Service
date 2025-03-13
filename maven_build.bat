./mvnw clean package
./mvnw package "-Dquarkus.package.type=fast-jar"
docker build -t vu0cay/ctu-routing-service:latest .
docker push vu0cay/ctu-routing-service:latest 
docker pull vu0cay/ctu-routing-service:latest 
docker-compose up -d

@REM docker-compose down