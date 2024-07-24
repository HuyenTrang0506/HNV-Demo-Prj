package com.hnv.db.job;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import com.hnv.api.main.Hnv_CfgHibernate;
import com.hnv.common.tool.ToolDBEntity;
import com.hnv.common.tool.ToolSet;
import com.hnv.db.EntityAbstract;
import com.hnv.db.EntityDAO;
import com.hnv.db.per.TaPerPerson;
import com.hnv.db.tpy.TaTpyCategoryEntity;
import com.hnv.db.tpy.TaTpyDocument;
import com.hnv.db.tpy.TaTpyInformation;
import com.hnv.def.DefDBExt;	


/**
 * TaMatMaterial by H&V SAS
 */
@Entity
@Table(name = DefDBExt.TA_JOB_REPORT )
public class TaJobReport extends EntityAbstract<TaJobReport> {

	private static final long serialVersionUID 	= 1L;
	private static final int  ENT_TYP			= DefDBExt.ID_TA_JOB_REPORT;
	
	public static final int	TYPE_01_JOB 		= 1;
	public static final int	TYPE_01_FINANCE 	= 100;
	public static final int	TYPE_01_SERVICE 	= 200;
	
	public static final int	TYPE_02_SINGLE      = 1;
	public static final int	TYPE_02_BOM 	    = 2;
	
	public static final int	STAT_NEW 			= 0;
	public static final int	STAT_ACTIVE 		= 1; 
	public static final int	STAT_REVIEW 		= 5; 
	public static final int	STAT_DELETED 		= 10;
	 
	//---------------------------List of Column from DB-----------------------------
	public static final String	COL_I_ID                              =	"I_ID";
		
	public static final String	COL_T_CODE_01                         =	"T_Code_01";
	public static final String	COL_T_CODE_02                         =	"T_Code_02";
	public static final String	COL_D_DATE_01                         =	"D_Date_01";
	public static final String	COL_D_DATE_02                         =	"D_Date_02";
	public static final String	COL_T_INFO_01                         =	"T_Info_01";
	public static final String	COL_T_INFO_02                         =	"T_Info_02";
	public static final String	COL_I_STATUS                       =	"I_Status";
	public static final String	COL_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	COL_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	COL_I_AUT_USER_03                     =	"I_Aut_User_03";
	public static final String	COL_I_Per_Manager                       =	"I_Per_Manager";
	public static final String	COL_F_Val_01                       =	"F_Val_01";
	public static final String	COL_F_Val_02                       =	"F_Val_02";
	public static final String	COL_F_Val_03                       =	"F_Val_03";
	public static final String	COL_F_Val_04                       =	"F_Val_04";
	
	


	//---------------------------List of ATTR of class-----------------------------
	public static final String	ATT_I_ID                              =	"I_ID";
	
	public static final String	ATT_T_CODE_01                         =	"T_Code_01";
	public static final String	ATT_T_CODE_02                         =	"T_Code_02";
	public static final String	ATT_D_DATE_01                         =	"D_Date_01";
	public static final String	ATT_D_DATE_02                         =	"D_Date_02";
	public static final String	ATT_T_INFO_01                         =	"T_Info_01";
	public static final String	ATT_T_INFO_02                         =	"T_Info_02";

	
	public static final String	ATT_D_DATE_03                         =	"D_Date_03";
	public static final String	ATT_D_DATE_04                         =	"D_Date_04";
	public static final String	ATT_I_STATUS                     =	"I_Status";
	
	public static final String	ATT_I_AUT_USER_01                     =	"I_Aut_User_01";
	public static final String	ATT_I_AUT_USER_02                     =	"I_Aut_User_02";
	public static final String	ATT_I_AUT_USER_03                     =	"I_Aut_User_03";
	public static final String	ATT_I_Per_Manager                       =	"I_Per_Manager";
	public static final String	ATT_F_Val_01                     =	"F_Val_01";
	public static final String	ATT_F_Val_02                     =	"F_Val_02";
	public static final String	ATT_F_Val_03                     =	"F_Val_03";
	public static final String	ATT_F_Val_04                     =	"F_Val_04";
	
	//----------------------------------------------------------------------------------------------

	public static final String	ATT_O_DOCUMENTS                       =	"O_Documents";
	public static final String	ATT_O_List_Report_Detail              =	"O_List_Report_Detail ";
	
	public static final String	ATT_O_List_Report_Resume                       	  =	" O_List_Report_Resume ";
	
	public static final String	ATT_O_Per_Create                      =	"O_Per_Create";
	public static final String	ATT_O_Per_Validate                      =	"O_Per_Validate";
	public static final String	ATT_O_Per_Manager                      =	"O_Per_Manager";
	public static final String	ATT_O_Per_Owner                      =	"O_Per_Owner";
	public static final String	ATT_O_Per_Man                      =	"O_Per_Man";
		

	//-------every entity class must initialize its DAO from here -----------------------------
	private 	static 	final boolean[] 			RIGHTS		= {true, true, true, true, false}; //canRead, canAdd, canUpd, canDel, del physique or flag only 
	private 	static 	final boolean[]				HISTORY		= {false, false, false}; //add, mod, del

	public		static 	final EntityDAO<TaJobReport> 	DAO;
	static	{
		DAO = new EntityDAO<TaJobReport>(Hnv_CfgHibernate.reqFactoryEMSession(Hnv_CfgHibernate.ID_FACT_MAIN), TaJobReport.class,RIGHTS, HISTORY, DefDBExt.TA_JOB_REPORT, DefDBExt.ID_TA_JOB_REPORT);
	}

	//-----------------------Class Attributs-------------------------
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name=COL_I_ID, nullable = false)
	private	Integer         I_ID;

	@Column(name=COL_T_CODE_01, nullable = true)
	    private String T_Code_01;

	    @Column(name=COL_T_CODE_02, nullable = true)
	    private String T_Code_02;

	    @Column(name=COL_D_DATE_01, nullable = true)
	    private Date D_Date_01=null;

	    @Column(name=COL_D_DATE_02, nullable = true)
	    private Date D_Date_02=null;

	    @Column(name=COL_T_INFO_01, nullable = true)
	    private String T_Info_01;

	    @Column(name=COL_T_INFO_02, nullable = true)
	    private String T_Info_02;

	    @Column(name=COL_I_STATUS, nullable = true)
	    private Integer I_Status;

	    @Column(name=COL_I_AUT_USER_01, nullable = true)
	    private Integer I_Aut_User_01;

	    @Column(name=COL_I_AUT_USER_02, nullable = true)
	    private Integer I_Aut_User_02;

	    @Column(name=COL_I_AUT_USER_03, nullable = true)
	    private Integer I_Aut_User_03;

	    @Column(name=COL_I_Per_Manager, nullable = true)
	    private Integer I_Per_Manager;

	    @Column(name=COL_F_Val_01, nullable = true)
	    private Double F_Val_01;

	    @Column(name=COL_F_Val_02, nullable = true)
	    private Double F_Val_02;

	    @Column(name=COL_F_Val_03, nullable = true)
	    private Double F_Val_03;

	    @Column(name=COL_F_Val_04, nullable = true)
	    private Double F_Val_04;
    
    
    
    
    
	//-----------------------Transient Variables-------------------------
	@Transient
	private	List<TaTpyDocument> 		O_Documents;

//	@Transient
//	private	List<TaMatMaterialDetail> 	O_Details;
//
//	@Transient
//	private List<TaTpyCategoryEntity>	O_Cats;
//	
//	@Transient
//	private	TaPerPerson 				O_Producer;
//
//	@Transient
//	private	TaPerPerson 				O_Manager;

	
	//---------------------Constructeurs-----------------------
	public TaJobReport(){}

	public TaJobReport(Map<String, Object> attrs) throws Exception {
		this.reqSetAttrFromMap(attrs);
		
	}

	//---------------------EntityInterface-----------------------
	@Override
	public Serializable reqRef() {
		return this.I_ID;

	}

	@Override
	public void doMergeWith(TaJobReport ent) {
		if (ent == this) return;
	
		  this.I_Status = ent.I_Status;
	        this.T_Code_01 = ent.T_Code_01;
	        this.T_Code_02 = ent.T_Code_02;
	        this.D_Date_01 = ent.D_Date_01;
	        this.D_Date_02 = ent.D_Date_02;
	        this.T_Info_01 = ent.T_Info_01;
	        this.T_Info_02 = ent.T_Info_02;
	        this.I_Aut_User_01 = ent.I_Aut_User_01;
	        this.I_Aut_User_02 = ent.I_Aut_User_02;
	        this.I_Aut_User_03 = ent.I_Aut_User_03;
	        this.I_Per_Manager = ent.I_Per_Manager;
	        this.F_Val_01 = ent.F_Val_01;
	        this.F_Val_02 = ent.F_Val_02;
	        this.F_Val_03 = ent.F_Val_03;
	        this.F_Val_04 = ent.F_Val_04;
	
		//---------------------Merge Transient Variables if exist-----------------------
	}

	@Override
	public boolean equals(Object o)  {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		boolean ok = false;

		ok = (I_ID == ((TaJobReport)o).I_ID);
		if (!ok) return ok;


		if (!ok) return ok;
		return ok;
	}

	@Override
	public int hashCode() {
		return this.I_ID;

	}

	public Integer reqID(){
		return this.I_ID;
	}


	public void doBuildDocuments(boolean forced) throws Exception {
		
		if (this.O_Documents != null && !forced) return;
		this.O_Documents = TaTpyDocument.reqTpyDocuments(ENT_TYP, I_ID, null, null);
		System.out.println("O_Documents: " + I_ID + " - " + O_Documents.size() +ENT_TYP);
	
	}
	


	
}
