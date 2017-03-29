ldap-server
===========

The ldap-service expects the following directories to be available:

* /data/slapd/database
* /data/slapd/config

The can be create using:

```
mkdir /data
mkdir /data/slapd
mkdir /data/slapd/database
mkdir /data/slapd/config
```

To start the LDAP server, run the following command:

```
./run.sh
```
