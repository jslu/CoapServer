package com.coap.server.main

import org.eclipse.californium.core.CoapResource
import org.eclipse.californium.core.CoapServer
import org.eclipse.californium.core.server.resources.CoapExchange
import java.net.SocketException


class Server
@Throws(SocketException::class)
constructor() : CoapServer() {
    init {
        add(EchoResource())
    }

    internal inner class EchoResource : CoapResource("echo" ) {
        init {
            attributes.title = "Echo Resource"
        }

        override fun handlePOST(exchange: CoapExchange) {
            val requestText = exchange.requestText
            println(requestText)
            exchange.respond(requestText)
        }

        override fun handleGET(exchange: CoapExchange) {
            exchange.respond("GET_REQUEST_SUCCESS" )
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val server = Server()
                server.start()
            } catch (e: SocketException) {
                System.err.println("Failed to initialize server: " + e.message)
            }
        }
    }
}