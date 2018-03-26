package fr.iutinfo.skeleton.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.iutinfo.skeleton.common.dto.CmdBDto;


public class CmdB {
    final static Logger logger = LoggerFactory.getLogger(CmdB.class);
    private static CmdB anonymous = new CmdB(1,1,15); 
	private int cno;
	private int uno;
	private int bno;
	private int qte;
	
	public CmdB(int cno, int uno, int bno, int qte) {
		this.cno = cno;
		this.uno = uno;
		this.bno = bno;
		this.qte = qte;
	}
	
	public CmdB() {
		
	}
	
	public CmdB(int uno, int bno,  int qte) {
		this.uno = uno;
		this.bno = bno;
		this.qte = qte;
	}
	
	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getUno() {
		return uno;
	}

	public void setUno(int uno) {
		this.uno = uno;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public boolean isInCmdBGroup() { 
        return !(cno == anonymous.getCno()); 
    } 

	public void initFromDto(CmdBDto dto) { 
        this.setCno(dto.getCno()); 
        this.setUno(dto.getUno());
        this.setBno(dto.getBno());
        this.setQte(dto.getQte());
    } 
 
    public CmdBDto convertToDto() { 
        CmdBDto dto = new CmdBDto(); 
        dto.setCno(this.cno); 
        dto.setUno(this.uno);
        dto.setBno(this.bno);
        dto.setQte(this.qte);
        return dto; 
    }

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	@Override
	public String toString() {
		return "CmdB [cno=" + cno + ", uno=" + uno + ", bno " + bno + ", qte=" + qte + "]";
	} 	
}