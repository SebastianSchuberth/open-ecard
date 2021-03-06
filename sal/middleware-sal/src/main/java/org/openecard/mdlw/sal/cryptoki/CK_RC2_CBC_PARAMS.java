package org.openecard.mdlw.sal.cryptoki;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * <i>native declaration : pkcs11_v2.40/pkcs11t.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class CK_RC2_CBC_PARAMS extends Structure {
	/**
	 * effective bits (1-1024)<br>
	 * C type : CK_ULONG
	 */
	public long ulEffectiveBits;
	public long getUlEffectiveBits() {
		return ulEffectiveBits;
	}
	public void setUlEffectiveBits(long ulEffectiveBits) {
		this.ulEffectiveBits = ulEffectiveBits;
	}
	/**
	 * IV for CBC mode<br>
	 * C type : CK_BYTE[8]
	 */
	public byte[] iv = new byte[8];
	public byte[] getIv() {
		return iv;
	}
	public void setIv(byte iv[]) {
		this.iv = iv;
	}
	public CK_RC2_CBC_PARAMS() {
		super();
	}
	 protected List<String> getFieldOrder() {
		return Arrays.asList("ulEffectiveBits", "iv");
	}
	/**
	 * @param ulEffectiveBits effective bits (1-1024)<br>
	 * C type : CK_ULONG<br>
	 * @param iv IV for CBC mode<br>
	 * C type : CK_BYTE[8]
	 */
	public CK_RC2_CBC_PARAMS(long ulEffectiveBits, byte iv[]) {
		super();
		this.ulEffectiveBits = ulEffectiveBits;
		if ((iv.length != this.iv.length)) 
			throw new IllegalArgumentException("Wrong array size !");
		this.iv = iv;
	}
	public CK_RC2_CBC_PARAMS(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends CK_RC2_CBC_PARAMS implements Structure.ByReference {
		
	};
	public static class ByValue extends CK_RC2_CBC_PARAMS implements Structure.ByValue {
		
	};
}
