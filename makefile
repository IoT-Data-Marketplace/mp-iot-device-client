build-and-push-to-dockerhub:
	docker build -t mp-iot-device-client .
	docker tag mp-iot-device-client:latest ddanijeld/mp-iot-device-client:latest
	docker push ddanijeld/mp-iot-device-client:latest


build-and-push-to-github:
	cat ~/GH_TOKEN.txt | docker login docker.pkg.github.com -u ddanijel --password-stdin
	docker build -t mp-iot-device-client .
	docker tag mp-iot-device-client:latest docker.pkg.github.com/iot-data-marketplace/mp-iot-device-client/mp-iot-device-client:latest
	docker push docker.pkg.github.com/iot-data-marketplace/mp-iot-device-client/mp-iot-device-client:latest