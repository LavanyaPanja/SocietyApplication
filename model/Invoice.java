package com.society.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;
@Entity



public class Invoice {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;
	    private String discription;
	    @Lob
	    @Column(length = 1000000)
	    private byte[] displayPic;
	    
	    public static  Invoice builder() {
            return new Invoice();
        }
	   
		public Invoice() {
			super();
			// TODO Auto-generated constructor stub
		}
		public Invoice(int id, String discription, byte[] displayPic) {
			super();
			this.id = id;
			this.discription = discription;
			this.displayPic = displayPic;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDiscription() {
			return discription;
		}
		public void setDiscription(String discription) {
			this.discription = discription;
		}
		public byte[] getDisplayPic() {
			return displayPic;
		}
		public void setDisplayPic(byte[] displayPic) {
			this.displayPic = displayPic;
		} public static class Builder {
	        private Long id;
	        private String discription;
	        private byte[] displayPic;

	        // Builder methods for setting fields
	        public Builder id(Long id) {
	            this.id = id;
	            return this;
	        }

	        public Builder discription(String discription) {
	            this.discription = discription;
	            return this;
	        }

	        public Builder displayPic(byte[] displayPic) {
	            this.displayPic = displayPic;
	            return this;
	        }

	        // Build method to create an instance of Invoice
	        public Invoice build() {
	            return new Invoice();
	        }
	    }
		
	    
}
