package com.dounine.yes.core.postman.example

enum class CodeStatus(val value:String) {
    C100("Continue"),
    C101("Switching Protocols"),
    C102("Processing (WebDAV) (RFC 2518)"),
    C103("Checkpoint"),
    C122("Request-URI too long"),
    C200("OK"),
    C201("Created"),
    C202("Accepted"),
    C203("Non-Authoritative Information (since HTTP/1.1)"),
    C204("No Content"),
    C205("Reset Content"),
    C302("Found"),
    C304("Not Modified"),
    C400("Bad Request"),
    C401("Unauthorized"),
    C402("Payment Required"),
    C403("Forbidden"),
    C404("Not Found"),
    C405("Method Not Allowed"),
    C406("Not Acceptable"),
    C500("Internal Server Error"),
    C502("Bad Gateway"),
    C504("Gateway Timeout")
}
