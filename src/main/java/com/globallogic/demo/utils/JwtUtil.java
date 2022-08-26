package com.globallogic.demo.utils;


import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import com.nimbusds.jose.jwk.RSAKey;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	public static final long SECOND_IN_MILLIS = 1000;
	public static final long MINUTE_IN_MILLIS = SECOND_IN_MILLIS * 60;
	public static final long HOUR_IN_MILLIS = MINUTE_IN_MILLIS * 60;
	public static final long DAY_IN_MILLIS = HOUR_IN_MILLIS * 24;
	private static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC0acsjYwpdVjnM"
			+ "UI7yhHO1yZiVDC94+3TB0Sgu5qItTVms+TFJjF0Hf6jX/WzxS09rGrdKqJD8NxFn"
			+ "sxllTZ4Sn+RvKmIubgi73UNpaoN/0XrTEmpIE3wCv/E4xwfdxrOM1JIEoM3oco0p"
			+ "zxqcY5i49buL+XuBi1+pL+s01qJIezS1moHOD+6fjKdab4+178vl+ilXBwSZ7ARN"
			+ "r2KYC/4+CXI99jg9YSmw082Yi6tFXa8XQYTwhy2PpDWIZJhpYajTxw9QwMJNNdXx"
			+ "ruqaDl4rz+4zwcq7rofbtCpH1wwNVBS347OenCB6g8f8y3DaInHha9eTZkfRpw7q"
			+ "ycSUbjS3AgMBAAECggEAHqzKPEcPsRUDGMkttPBuWB9h0R3USIbAsF+mrGrMclVv"
			+ "hOiNx8qU2ryK8caGKnS6mSReu9Pzat7WF8Ks87uBv/rttTUIoopU89RILPlOSIJO"
			+ "lurPpVeiwzjtg9DbdordZnmSJjVNLVOkytIK3UJV6kTl0YPmyvzuk+6w2DgSOa5n"
			+ "4vttmCTVodrAun+r9vwai+UPAUk18VczlyHZ0UP82KVLUrc9l7HIZ/eKrIwWAp2v"
			+ "iI3d2j6kuvAw4sfFR4PEvWn0AIBlx7cdxHxKVqBTvieEbT628MXGuns9ntwYU2cX"
			+ "OHa3XSsSF1KrO3PYlXU5Ulvpgq0gDOLnhN7g/+OSuQKBgQDmW1x4U2sVzU27PTif"
			+ "o8S+5guQWxHzdHZ0mEmxI1SFUeSYAc4Vqj8RhouWT6Gzuyg+HFwJeFj0QHP+is7p"
			+ "BbNRdfCiZgZNNgQcXrHVW3Jn2vtxdQ04iEHeoQEDdXvQ9bgQCKQ7qFg60eBmu5f2"
			+ "Ha480Ex4Z1Zp6HdM/ADBivNl2wKBgQDIfyeIPKCIcTHKbakbtORTDDWRGJ+8baPy"
			+ "lM9UP1o30oZPJldomebjTr498fxPd+2quznhMjCuwynJPn6Mghj7JPvdFrzLVShd"
			+ "BVRGCxvrUrU6rU+MxI7hgoR3TCvghFvt548OAhwff5bM4qk4frQ9tveJqR5BQB8X"
			+ "j/BqVXEZVQKBgQDhxuYJIWmxwrnPLSAUh8VMfdkTsc8UWS/I84I21jP/P9bXjd1Z"
			+ "qrWKDd8+geoxaNz2k4E53vUA4TNPZumJxwaMbxZddbXzc1/wFngicDDfzefouhst"
			+ "L4+wDwoZQ8sJcezn+jBOMJ/Zdt+EkZVNqqWeAWMSF+bAgxJ7J/vttqz9RQKBgDD1"
			+ "M68FtuF6WoEmo+ubEUHiMlZa3+6AC0+Z3MKisaH960P6hc+J/SI2QSgZJdY137gX"
			+ "/bsqqU6TY2m7s+UVg4oX7tR+z90Kz09flMOBxPCCgrRwS4OBH66bU0NaO0CYsWX3"
			+ "bCwsgbeDTUx8cHvWHKcRPYLE5Blk0fLL+BXPBU39AoGBAONmV6PcED2hiz7gVbEM"
			+ "oqxAo9yfsQaD0VRBtQqkIHe5Nz93rPx765gv7g1dShu+9dplVE3w/PAch4L4m8zV"
			+ "opd5RCAprgTDKManSY+NhvUTgRIETQV16DIw+V8v8vLCN+azNS91gK+76RGM6kjU"
			+ "jfmXo1IiV2BDm/v3qo21LMfv";
	
	private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtGnLI2MKXVY5zFCO8oRz"
			+ "tcmYlQwvePt0wdEoLuaiLU1ZrPkxSYxdB3+o1/1s8UtPaxq3SqiQ/DcRZ7MZZU2e"
			+ "Ep/kbypiLm4Iu91DaWqDf9F60xJqSBN8Ar/xOMcH3cazjNSSBKDN6HKNKc8anGOY"
			+ "uPW7i/l7gYtfqS/rNNaiSHs0tZqBzg/un4ynWm+Pte/L5fopVwcEmewETa9imAv+"
			+ "PglyPfY4PWEpsNPNmIurRV2vF0GE8Ictj6Q1iGSYaWGo08cPUMDCTTXV8a7qmg5e"
			+ "K8/uM8HKu66H27QqR9cMDVQUt+OznpwgeoPH/Mtw2iJx4WvXk2ZH0acO6snElG40"
			+ "twIDAQAB";

	private JwtUtil() {
	}

	public static String createJWT(String id, String issuer, String subject) {
		// The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.RS256;

		long nowMillis = System.currentTimeMillis();

		// We will sign our JWT with our ApiKey secret
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(PRIVATE_KEY));
		KeyFactory rsaFact = null;
		try {
			rsaFact = KeyFactory.getInstance("RSA");
			RSAPrivateKey key = (RSAPrivateKey) rsaFact.generatePrivate(spec);

			JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(new Date(nowMillis)).setSubject(subject)
					.setIssuer(issuer).setExpiration(new Date(nowMillis + DAY_IN_MILLIS))
					.signWith(signatureAlgorithm, key);

			return builder.compact();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			throw new IllegalStateException(e);
		}
	}
	
	public static  RSAKey generateRsa() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
	    RSAPublicKey publicKey = null;
	    RSAPrivateKey privateKey = null;
	    
        String privateKeyContent = PRIVATE_KEY;
        String publicKeyContent = PUBLIC_KEY;

        KeyFactory kf = KeyFactory.getInstance("RSA");

        PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
        PrivateKey privKey = kf.generatePrivate(keySpecPKCS8);
        privateKey=(RSAPrivateKey) privKey;

        X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
        RSAPublicKey pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);
        publicKey=pubKey;
        System.out.println(privKey);
        System.out.println(pubKey);
	    
	    return new RSAKey.Builder(publicKey)
	      .privateKey(privateKey)
	      .keyID(UUID.randomUUID().toString())
	      .build();
	}

}