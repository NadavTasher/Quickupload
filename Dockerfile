FROM php:7.3-apache
# Install java
RUN apt-get update && apt-get install -y default-jre
# Install supervisor
RUN apt-get -y install supervisor
# Enable mods
RUN a2enmod headers
# Copy WebApp to /var/www/html
COPY src /var/www/html
# Change ownership of /var/www
RUN chown www-data /var/www/ -R
# Change permissions of /var/www
RUN chmod 775 /var/www/ -R
# Copy executable to /home
COPY src/java/build/libs/Quickupload.jar /home/app.jar
# Change ownership
RUN chown root /home/ -R
# Change permissions
RUN chmod 775 /home/ -R
# Copy supervisor configuration
COPY configuration/supervisord.conf /etc/supervisor/conf.d/supervisord.conf
# Start supervisor
CMD ["supervisord"]