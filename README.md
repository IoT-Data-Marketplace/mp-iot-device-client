## Dependencies
- https://pi4j.com/

## Install Docker on RaspberryPI

- sudo apt-get update && sudo apt-get upgrade
- curl -fsSL https://get.docker.com -o get-docker.sh
- sudo sh get-docker.sh
- sudo usermod -aG docker pi
- docker version

Docker Compose Setup
- sudo apt-get install -y libffi-dev libssl-dev
- sudo apt-get install -y python3 python3-pip
- sudo apt-get remove python-configparser
- sudo pip3 install docker-compose


## Run the client:

```
docker run \
--privileged \
--env MP_IOT_DEVICE_CLIENT_PLATFORM_ENDPOINT="https://iot-data-mp.com/graphql" \
--env MP_IOT_DEVICE_CLIENT_SENSOR_ID="<SENSOR_CONTRACT_ADDRESS>" \
docker.pkg.github.com/iot-data-marketplace/mp-iot-device-client/mp-iot-device-client:latest
```
