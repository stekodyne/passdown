#!/bin/bash -e
docker run \
--name ldap-service \
--hostname ldap-service \
-p 636:636 \
-p 389:389 \
--env LDAP_ORGANISATION="Stekodyne LLC" \
--env LDAP_DOMAIN="stekodyne.com" \
--env LDAP_ADMIN_PASSWORD="admin" \
--volume /data/slapd/database:/var/lib/ldap \
--volume /data/slapd/config:/etc/ldap/slapd.d \
--detach osixia/openldap:latest

LDAP_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" ldap-service)

docker run \
--name phpldapadmin-service \
--hostname phpldapadmin-service \
--env PHPLDAPADMIN_LDAP_HOSTS=${LDAP_IP} \
--detach osixia/phpldapadmin:latest

PHPLDAP_IP=$(docker inspect -f "{{ .NetworkSettings.IPAddress }}" phpldapadmin-service)

echo "Go to: https://$PHPLDAP_IP"
echo "Login DN: cn=admin,dc=stekodyne,dc=com"
echo "Password: admin"
