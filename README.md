# Quickupload

Quickupload is an open-source project designed to help people with terminal access upload files with no installations.

## Installation
### Method 1: Using the Docker Hub repository
Install [docker](https://www.docker.com/) on your machine.

Run the following command:
```bash
docker run -p 80:80 -p 1:1 -e URL=http://address/files/ --name quickupload-container --restart unless-stopped -d nadavtasher/quickupload:latest
```
### Method 2: Building a docker image from source
Install [docker](https://www.docker.com/) on your machine.

[Clone the repository](https://github.com/NadavTasher/Quickupload/archive/master.zip) or [download the latest release](https://github.com/NadavTasher/Quickupload/releases/latest), enter the extracted directory, then run the following commands:
```bash
docker build . -t quickupload
docker run -p 80:80 -p 1:1 -e URL=http://address/files/ --name quickupload-container --restart unless-stopped -d quickupload
```

## Usage
Open `http://address` in a web browser, and try the upload command.

## Contributing
Pull requests are welcome, but only for smaller changer.
For larger changes, open an issue so that we could discuss the change.

Bug reports and vulnerabilities are welcome too. 
## License
[MIT](https://choosealicense.com/licenses/mit/)