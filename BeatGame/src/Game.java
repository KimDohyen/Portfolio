import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Game extends Thread {
	//노트 판정 라인
	private Image judgementLineImage = new ImageIcon(Main.class.getResource("./images/judgementLine.png")).getImage();	
	//게임 정보
	private Image gameInfoImage = new ImageIcon(Main.class.getResource("./images/gameInfo.png")).getImage();
	//노트 경로 이미지
	private Image noteRouteDImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();	
	private Image noteRouteFImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();	
	private Image noteRouteJImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();	
	private Image noteRouteKImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();		
		
	//키 이미지
	private Image dKeyImage = new ImageIcon(Main.class.getResource("./images/dKey.png")).getImage();
	private Image fKeyImage = new ImageIcon(Main.class.getResource("./images/fKey.png")).getImage();
	private Image jKeyImage = new ImageIcon(Main.class.getResource("./images/jKey.png")).getImage();
	private Image kKeyImage = new ImageIcon(Main.class.getResource("./images/kKey.png")).getImage();
	
	//판정 이미지
	private Image hitImage;
	
	private String titleName;
	private String musicTitle;
	private Music gameMusic;
	private int Score = 0;
	private String ScorePrt = Integer.toString(Score);
	
	ArrayList<Note> noteList = new ArrayList<Note>();
	
	public Game(String titleName, String musicTitle) {
		this.titleName = titleName;
		this.musicTitle = musicTitle;
		gameMusic = new Music(BeatGame.Title[BeatGame.cnt], this.musicTitle, false);
	}
		
	public void screenDraw(Graphics2D g) {
		g.drawImage(noteRouteDImage, 200, 30, null);
		g.drawImage(noteRouteFImage, 270, 30, null);
		g.drawImage(noteRouteJImage, 340, 30, null);
		g.drawImage(noteRouteKImage, 410, 30, null);
		g.drawImage(gameInfoImage, 980, 30, null);
		g.drawImage(judgementLineImage, 200, 580, null);
		for(int i = 0; i < noteList.size(); i++)
		{
			Note note = noteList.get(i);
			if(note.getY() > 620) {
				hitImage =new ImageIcon(Main.class.getResource("./images/hitMiss.png")).getImage();
			}
			if(!note.isProceeded()) { // 노트 판정 시 삭제
				noteList.remove(i);
				i--;
			} else {
				note.screenDraw(g);
				
			}
		}
		g.drawImage(dKeyImage, 200, 605, null);
		g.drawImage(fKeyImage, 270, 605, null);
		g.drawImage(jKeyImage, 340, 605, null);
		g.drawImage(kKeyImage, 410, 605, null);
		g.setColor(Color.white);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("HCR Dotum", Font.BOLD, 100));
		g.drawImage(hitImage, 250, 420, null);
		g.drawString(ScorePrt, 900, 110);
	}
	
	public void pressD() {
		judge("D");
		noteRouteDImage = new ImageIcon(Main.class.getResource("./images/noteRoutePressed.png")).getImage();
		new Music("main_music","hitnormal.mp3", false).start();
	}
	public void releaseD() {
		noteRouteDImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();
	}
	public void pressF() {
		judge("F");
		noteRouteFImage = new ImageIcon(Main.class.getResource("./images/noteRoutePressed.png")).getImage();
		new Music("main_music","hitnormal.mp3", false).start();
	}
	public void releaseF() {
		noteRouteFImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();
	}
	public void pressJ() {
		judge("J");
		noteRouteJImage = new ImageIcon(Main.class.getResource("./images/noteRoutePressed.png")).getImage();
		new Music("main_music","hitnormal.mp3", false).start();
	}
	public void releaseJ() {
		noteRouteJImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();
	}
	public void pressK() {
		judge("K");
		noteRouteKImage = new ImageIcon(Main.class.getResource("./images/noteRoutePressed.png")).getImage();
		new Music("main_music","hitnormal.mp3", false).start();
	}
	public void releaseK() {
		noteRouteKImage = new ImageIcon(Main.class.getResource("./images/noteRoute.png")).getImage();
	}
	
	@Override
	public void run() {
		dropNotes();
	}
	
	public void close() {
		gameMusic.close();
		this.interrupt();
	}
	
	public void dropNotes() {
		Beat[] beats = null;
		if(titleName.equals("Faded")) {
			int startTime = 0;
			
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
 		}
		else if(titleName.equals("Blue Zenith")) {
			int startTime = 0;
			beats = new Beat[] {
					new Beat(startTime, "D"),
			};
		}
		else if(titleName.equals("Flower Dance")) {
			int startTime = 24497;
			int gap = 150;
			beats = new Beat[] {
					new Beat(startTime + gap * 0, "K"), //26.597
					new Beat(startTime + gap * 2, "D"),
					new Beat(startTime + gap * 5, "F"),
					new Beat(startTime + gap * 6, "J"),
					new Beat(startTime + gap * 7, "K"),
					new Beat(startTime + gap * 9, "D"),
					new Beat(startTime + gap * 11, "F"),
					new Beat(startTime + gap * 13, "K"),
					new Beat(startTime + gap * 17, "J"),
					new Beat(startTime + gap * 20, "F"),
					new Beat(startTime + gap * 21, "K"),
					new Beat(startTime + gap * 23, "J"),
					new Beat(startTime + gap * 24, "D"),
					new Beat(startTime + gap * 26, "F"),
					new Beat(startTime + gap * 28, "K"),
					new Beat(startTime + gap * 30, "J"),
					new Beat(startTime + gap * 32, "D"), //31.397
					new Beat(startTime + gap * 34, "F"),
					new Beat(startTime + gap * 36, "K"),
					new Beat(startTime + gap * 38, "D"),
					new Beat(startTime + gap * 40, "F"),
					new Beat(startTime + gap * 42, "J"),
					new Beat(startTime + gap * 44, "D"),
					new Beat(startTime + gap * 46, "K"),
					new Beat(startTime + gap * 48, "F"),
					new Beat(startTime + gap * 50, "D"),
					new Beat(startTime + gap * 52, "F"),
					new Beat(startTime + gap * 54, "J"),
					new Beat(startTime + gap * 56, "K"),
					new Beat(startTime + gap * 58, "D"),
					new Beat(startTime + gap * 60, "F"),
					new Beat(startTime + gap * 62, "K"),
					new Beat(startTime + gap * 64, "K"),
					new Beat(startTime + gap * 66, "D"),
					new Beat(startTime + gap * 69, "F"),
					new Beat(startTime + gap * 70, "J"),
					new Beat(startTime + gap * 71, "K"),
					new Beat(startTime + gap * 72, "D"),
					new Beat(startTime + gap * 74, "F"),
					new Beat(startTime + gap * 76, "K"),
					new Beat(startTime + gap * 80, "D"),
					new Beat(startTime + gap * 82, "F"),
					new Beat(startTime + gap * 84, "J"),
					new Beat(startTime + gap * 86, "D"),
					new Beat(startTime + gap * 88, "K"),
					new Beat(startTime + gap * 90, "J"),
					new Beat(startTime + gap * 92, "F"),
					new Beat(startTime + gap * 94, "D"), //40.697
					new Beat(startTime + gap * 96, "K"),
					new Beat(startTime + gap * 98, "F"),
					new Beat(startTime + gap * 100, "J"),
					new Beat(startTime + gap * 104, "D"),
					new Beat(startTime + gap * 106, "J"),
					new Beat(startTime + gap * 108, "F"),
					new Beat(startTime + gap * 112, "D"),
					new Beat(startTime + gap * 114, "F"),
					new Beat(startTime + gap * 116, "J"),
					new Beat(startTime + gap * 118, "D"),
					new Beat(startTime + gap * 120, "K"),
					new Beat(startTime + gap * 122, "F"),
					new Beat(startTime + gap * 124, "J"),
					new Beat(startTime + gap * 124, "K"),
					new Beat(startTime + gap * 126, "F"),
					new Beat(startTime + gap * 128, "D"),
					new Beat(startTime + gap * 128, "K"),
					new Beat(startTime + gap * 130, "J"),
					new Beat(startTime + gap * 132, "D"),
					new Beat(startTime + gap * 132, "F"),
					new Beat(startTime + gap * 134, "K"),
					new Beat(startTime + gap * 136, "D"),
					new Beat(startTime + gap * 138, "F"),
					new Beat(startTime + gap * 138, "F"),
					new Beat(startTime + gap * 140, "J"),
					new Beat(startTime + gap * 140, "K"),
					new Beat(startTime + gap * 142, "F"),
					new Beat(startTime + gap * 144, "K"),
					new Beat(startTime + gap * 146, "J"),
					new Beat(startTime + gap * 148, "D"),
					new Beat(startTime + gap * 148, "F"),
					new Beat(startTime + gap * 150, "K"),
					new Beat(startTime + gap * 152, "F"),
					new Beat(startTime + gap * 154, "J"),
					new Beat(startTime + gap * 156, "D"),
					new Beat(startTime + gap * 156, "K"),
					new Beat(startTime + gap * 158, "F"),
					new Beat(startTime + gap * 160, "J"),
					new Beat(startTime + gap * 162, "D"),
					new Beat(startTime + gap * 164, "J"),
					new Beat(startTime + gap * 164, "K"), //51.197
					new Beat(startTime + gap * 166, "F"),
					new Beat(startTime + gap * 168, "D"),
					new Beat(startTime + gap * 170, "K"),
					new Beat(startTime + gap * 172, "D"),
					new Beat(startTime + gap * 172, "F"),
					new Beat(startTime + gap * 174, "J"),
					new Beat(startTime + gap * 176, "D"),
					new Beat(startTime + gap * 178, "F"),
					new Beat(startTime + gap * 180, "J"),
					new Beat(startTime + gap * 180, "K"),
					new Beat(startTime + gap * 182, "D"),
					new Beat(startTime + gap * 184, "J"),
					new Beat(startTime + gap * 186, "F"),
					new Beat(startTime + gap * 188, "D"),
					new Beat(startTime + gap * 188, "K"), //54.797
					new Beat(startTime + gap * 190, "F"),
					new Beat(startTime + gap * 192, "J"),
					new Beat(startTime + gap * 194, "D"),
					new Beat(startTime + gap * 196, "F"),
					new Beat(startTime + gap * 196, "K"),
					new Beat(startTime + gap * 198, "J"),
					new Beat(startTime + gap * 200, "D"),
					new Beat(startTime + gap * 202, "F"),
					new Beat(startTime + gap * 204, "J"),
					new Beat(startTime + gap * 204, "K"),
					new Beat(startTime + gap * 206, "D"),
					new Beat(startTime + gap * 208, "K"),
					new Beat(startTime + gap * 210, "F"),
					new Beat(startTime + gap * 212, "D"),
					new Beat(startTime + gap * 212, "J"),
					new Beat(startTime + gap * 214, "F"),
					new Beat(startTime + gap * 216, "K"),
					new Beat(startTime + gap * 218, "J"),
					new Beat(startTime + gap * 220, "D"),
					new Beat(startTime + gap * 220, "F"),
					new Beat(startTime + gap * 222, "K"),
					new Beat(startTime + gap * 224, "F"),
					new Beat(startTime + gap * 226, "J"),
					new Beat(startTime + gap * 228, "D"),
					new Beat(startTime + gap * 228, "K"),
					new Beat(startTime + gap * 230, "F"),
					new Beat(startTime + gap * 232, "J"),
					new Beat(startTime + gap * 234, "F"),
					new Beat(startTime + gap * 236, "D"),
					new Beat(startTime + gap * 236, "K"),
					new Beat(startTime + gap * 238, "J"),
					new Beat(startTime + gap * 240, "F"), //62.597
					new Beat(startTime + gap * 242, "K"),
					new Beat(startTime + gap * 244, "D"),
					new Beat(startTime + gap * 244, "J"),
					new Beat(startTime + gap * 246, "K"),
					new Beat(startTime + gap * 248, "F"),
					new Beat(startTime + gap * 250, "J"),
					new Beat(startTime + gap * 252, "D"),
					new Beat(startTime + gap * 252, "K"),
					new Beat(startTime + gap * 254, "F"),
					new Beat(startTime + gap * 256, "K"),
					new Beat(startTime + gap * 258, "J"),
					new Beat(startTime + gap * 260, "D"),
					new Beat(startTime + gap * 260, "F"),
					new Beat(startTime + gap * 261, "K"),
					new Beat(startTime + gap * 262, "J"),
					new Beat(startTime + gap * 263, "F"),
					new Beat(startTime + gap * 264, "D"),
					new Beat(startTime + gap * 266, "F"),
					new Beat(startTime + gap * 268, "J"),
					new Beat(startTime + gap * 268, "K"),
					new Beat(startTime + gap * 272, "D"),
					new Beat(startTime + gap * 275, "F"),
					new Beat(startTime + gap * 276, "K"),
					new Beat(startTime + gap * 279, "J"),
					new Beat(startTime + gap * 280, "D"),
					new Beat(startTime + gap * 284, "J"),
					new Beat(startTime + gap * 284, "K"), //69.197
					new Beat(startTime + gap * 288, "D"),
					new Beat(startTime + gap * 292, "J"),
					new Beat(startTime + gap * 292, "K"),
					new Beat(startTime + gap * 296, "D"),
					new Beat(startTime + gap * 298, "K"),
					new Beat(startTime + gap * 300, "D"),
					new Beat(startTime + gap * 300, "F"),
					new Beat(startTime + gap * 302, "J"),
					new Beat(startTime + gap * 304, "F"),
					new Beat(startTime + gap * 306, "J"),
					new Beat(startTime + gap * 308, "D"),
					new Beat(startTime + gap * 308, "K"), //72.797
					new Beat(startTime + gap * 310, "F"),
					new Beat(startTime + gap * 312, "J"),
					new Beat(startTime + gap * 314, "D"),
					new Beat(startTime + gap * 316, "J"),
					new Beat(startTime + gap * 316, "K"),
					new Beat(startTime + gap * 318, "F"),
					new Beat(startTime + gap * 319, "J"),
					new Beat(startTime + gap * 320, "D"),
					new Beat(startTime + gap * 322, "F"),
					new Beat(startTime + gap * 324, "J"),
					new Beat(startTime + gap * 324, "K"),
					new Beat(startTime + gap * 325, "D"),
					new Beat(startTime + gap * 326, "F"),
					new Beat(startTime + gap * 327, "J"),
					new Beat(startTime + gap * 328, "K"),
					new Beat(startTime + gap * 330, "F"),
					new Beat(startTime + gap * 332, "D"),
					new Beat(startTime + gap * 332, "J"),
					new Beat(startTime + gap * 336, "K"),
					new Beat(startTime + gap * 338, "F"),
					new Beat(startTime + gap * 340, "J"),
					new Beat(startTime + gap * 340, "K"),
					new Beat(startTime + gap * 342, "D"),
					new Beat(startTime + gap * 344, "K"),
					new Beat(startTime + gap * 346, "J"),
					new Beat(startTime + gap * 348, "F"),
					new Beat(startTime + gap * 348, "K"),
					new Beat(startTime + gap * 350, "D"),
					new Beat(startTime + gap * 352, "J"),
					new Beat(startTime + gap * 354, "K"),
					new Beat(startTime + gap * 356, "D"),
					new Beat(startTime + gap * 356, "F"),
					new Beat(startTime + gap * 360, "F"),
					new Beat(startTime + gap * 362, "D"), //80.897
					new Beat(startTime + gap * 364, "J"),
					new Beat(startTime + gap * 364, "K"),
					new Beat(startTime + gap * 368, "F"),
					new Beat(startTime + gap * 370, "J"),
					new Beat(startTime + gap * 372, "D"),
					new Beat(startTime + gap * 372, "K"),
					new Beat(startTime + gap * 374, "J"),
					new Beat(startTime + gap * 376, "F"),
					new Beat(startTime + gap * 378, "J"),
					new Beat(startTime + gap * 380, "D"),
					new Beat(startTime + gap * 380, "K"),
					new Beat(startTime + gap * 384, "J"),
					new Beat(startTime + gap * 386, "F"), //1.24.497
					new Beat(startTime + gap * 388, "J"),
					new Beat(startTime + gap * 388, "K"),
					new Beat(startTime + gap * 389, "D"),
					new Beat(startTime + gap * 390, "F"),
					new Beat(startTime + gap * 391, "K"),
					new Beat(startTime + gap * 392, "F"),
					new Beat(startTime + gap * 394, "J"),
					new Beat(startTime + gap * 396, "D"),
					new Beat(startTime + gap * 396, "K"), //1.25.997
					new Beat(startTime + gap * 400, "F"),
					new Beat(startTime + gap * 403, "J"),
					new Beat(startTime + gap * 404, "D"),
					new Beat(startTime + gap * 407, "F"),
					new Beat(startTime + gap * 408, "K"),
					new Beat(startTime + gap * 412, "J"),
					new Beat(startTime + gap * 412, "K"),
					new Beat(startTime + gap * 416, "D"),
					new Beat(startTime + gap * 420, "F"),
					new Beat(startTime + gap * 420, "K"),
					new Beat(startTime + gap * 424, "J"),
					new Beat(startTime + gap * 426, "D"),
					new Beat(startTime + gap * 428, "F"),
					new Beat(startTime + gap * 428, "K"),
					new Beat(startTime + gap * 430, "J"),
					new Beat(startTime + gap * 432, "D"),
					new Beat(startTime + gap * 434, "F"),
					new Beat(startTime + gap * 436, "J"),
					new Beat(startTime + gap * 436, "K"),
					new Beat(startTime + gap * 438, "F"), //92.297
					new Beat(startTime + gap * 440, "K"),
					new Beat(startTime + gap * 442, "J"),
					new Beat(startTime + gap * 444, "D"),
					new Beat(startTime + gap * 444, "F"),
					new Beat(startTime + gap * 446, "K"),
					new Beat(startTime + gap * 447, "D"),
					new Beat(startTime + gap * 448, "J"),
					new Beat(startTime + gap * 450, "F"),
					new Beat(startTime + gap * 452, "D"),
					new Beat(startTime + gap * 452, "K"),
					new Beat(startTime + gap * 453, "J"),
					new Beat(startTime + gap * 454, "D"),
					new Beat(startTime + gap * 455, "F"),
					new Beat(startTime + gap * 456, "J"),
					new Beat(startTime + gap * 458, "D"),
					new Beat(startTime + gap * 460, "F"),
					new Beat(startTime + gap * 460, "K"),
					new Beat(startTime + gap * 464, "D"),
					new Beat(startTime + gap * 464, "K"),
					new Beat(startTime + gap * 466, "J"), //96.497
					new Beat(startTime + gap * 468, "F"),
					new Beat(startTime + gap * 468, "K"),
					new Beat(startTime + gap * 470, "D"),
					new Beat(startTime + gap * 472, "K"),
					new Beat(startTime + gap * 473, "F"),
					new Beat(startTime + gap * 474, "J"),
					new Beat(startTime + gap * 475, "D"),
					new Beat(startTime + gap * 476, "K"),
					new Beat(startTime + gap * 477, "D"),
					new Beat(startTime + gap * 478, "J"),
					new Beat(startTime + gap * 479, "F"),
					new Beat(startTime + gap * 480, "J"),
					new Beat(startTime + gap * 480, "K"),
					new Beat(startTime + gap * 482, "F"),
					new Beat(startTime + gap * 484, "D"),
					new Beat(startTime + gap * 488, "J"),
					new Beat(startTime + gap * 490, "F"),
					new Beat(startTime + gap * 492, "D"),
					new Beat(startTime + gap * 496, "K"),
					new Beat(startTime + gap * 498, "D"), //101.297
					new Beat(startTime + gap * 500, "F"),
					new Beat(startTime + gap * 502, "J"),
					new Beat(startTime + gap * 504, "K"),
					new Beat(startTime + gap * 506, "F"),
					new Beat(startTime + gap * 508, "J"),
					new Beat(startTime + gap * 508, "K"),
					new Beat(startTime + gap * 510, "F"),
					new Beat(startTime + gap * 512, "D"),
					new Beat(startTime + gap * 512, "K"),
					new Beat(startTime + gap * 514, "F"),
					new Beat(startTime + gap * 516, "J"),
					new Beat(startTime + gap * 516, "K"),
					new Beat(startTime + gap * 518, "D"),
					new Beat(startTime + gap * 520, "K"),
					new Beat(startTime + gap * 522, "J"),
					new Beat(startTime + gap * 524, "D"),
					new Beat(startTime + gap * 524, "F"),
					new Beat(startTime + gap * 526, "K"),
					new Beat(startTime + gap * 527, "D"),
					new Beat(startTime + gap * 528, "J"),
					new Beat(startTime + gap * 530, "F"),
					new Beat(startTime + gap * 532, "J"),
					new Beat(startTime + gap * 532, "K"),
					new Beat(startTime + gap * 534, "D"), //106.697
					new Beat(startTime + gap * 536, "J"),
					new Beat(startTime + gap * 538, "F"),
					new Beat(startTime + gap * 540, "D"),
					new Beat(startTime + gap * 540, "K"),
					new Beat(startTime + gap * 542, "J"),
					new Beat(startTime + gap * 543, "D"),
					new Beat(startTime + gap * 544, "K"),
					new Beat(startTime + gap * 546, "F"),
					new Beat(startTime + gap * 548, "J"),
					new Beat(startTime + gap * 548, "K"),
					new Beat(startTime + gap * 550, "D"),
					new Beat(startTime + gap * 552, "J"),
					new Beat(startTime + gap * 554, "F"),
					new Beat(startTime + gap * 556, "D"),
					new Beat(startTime + gap * 556, "K"),
					new Beat(startTime + gap * 558, "J"),
					new Beat(startTime + gap * 560, "D"),
					new Beat(startTime + gap * 562, "F"),
					new Beat(startTime + gap * 564, "J"),
					new Beat(startTime + gap * 564, "K"),
					new Beat(startTime + gap * 566, "F"),
					new Beat(startTime + gap * 568, "D"),
					new Beat(startTime + gap * 570, "F"),
					new Beat(startTime + gap * 570, "K"), //106.697
					new Beat(startTime + gap * 572, "D"),
					new Beat(startTime + gap * 572, "J"),
					new Beat(startTime + gap * 576, "D"),
					new Beat(startTime + gap * 578, "F"),
					new Beat(startTime + gap * 580, "D"),
					new Beat(startTime + gap * 580, "K"),
					new Beat(startTime + gap * 582, "J"),
					new Beat(startTime + gap * 584, "D"),
					new Beat(startTime + gap * 586, "F"),
					new Beat(startTime + gap * 588, "J"),
					new Beat(startTime + gap * 588, "K"),
					new Beat(startTime + gap * 590, "D"),
					new Beat(startTime + gap * 591, "J"),
					new Beat(startTime + gap * 592, "F"),
					new Beat(startTime + gap * 594, "K"),
					new Beat(startTime + gap * 595, "J"),
					new Beat(startTime + gap * 596, "D"),
					new Beat(startTime + gap * 597, "F"),
					new Beat(startTime + gap * 598, "J"),
					new Beat(startTime + gap * 599, "K"),
					new Beat(startTime + gap * 600, "D"),
					new Beat(startTime + gap * 602, "F"),
					new Beat(startTime + gap * 604, "J"),
					new Beat(startTime + gap * 604, "K"), //117.197
					new Beat(startTime + gap * 606, "F"),
					new Beat(startTime + gap * 607, "K"),
					new Beat(startTime + gap * 608, "D"),
					new Beat(startTime + gap * 610, "J"),
					new Beat(startTime + gap * 611, "F"),
					new Beat(startTime + gap * 612, "D"),
					new Beat(startTime + gap * 613, "K"),
					new Beat(startTime + gap * 614, "F"),
					new Beat(startTime + gap * 616, "J"),
					new Beat(startTime + gap * 617, "D"),
					new Beat(startTime + gap * 619, "F"),
					new Beat(startTime + gap * 620, "K"),
					new Beat(startTime + gap * 622, "J"),
					new Beat(startTime + gap * 623, "F"),
					new Beat(startTime + gap * 624, "D"),
					new Beat(startTime + gap * 626, "K"),
					new Beat(startTime + gap * 628, "D"),
					new Beat(startTime + gap * 628, "F"),
					new Beat(startTime + gap * 630, "K"),
					new Beat(startTime + gap * 632, "J"),
					new Beat(startTime + gap * 634, "D"),
					new Beat(startTime + gap * 634, "J"),
					new Beat(startTime + gap * 636, "F"),
					new Beat(startTime + gap * 636, "J"),
					new Beat(startTime + gap * 640, "D"),
					new Beat(startTime + gap * 640, "J"),
					new Beat(startTime + gap * 642, "F"),
					new Beat(startTime + gap * 643, "J"),
					new Beat(startTime + gap * 644, "K"),
					new Beat(startTime + gap * 646, "D"),
					new Beat(startTime + gap * 647, "F"),
					new Beat(startTime + gap * 648, "J"), //2.03.797
					new Beat(startTime + gap * 650, "F"),
					new Beat(startTime + gap * 651, "K"),
					new Beat(startTime + gap * 652, "D"),
					new Beat(startTime + gap * 654, "J"),
					new Beat(startTime + gap * 655, "D"),
					new Beat(startTime + gap * 656, "K"),
					new Beat(startTime + gap * 658, "F"),
					new Beat(startTime + gap * 660, "J"),
					new Beat(startTime + gap * 662, "D"),
					new Beat(startTime + gap * 664, "K"),
					new Beat(startTime + gap * 666, "J"),
					new Beat(startTime + gap * 668, "D"),
					new Beat(startTime + gap * 670, "F"),
					new Beat(startTime + gap * 672, "J"),
					new Beat(startTime + gap * 674, "D"),
					new Beat(startTime + gap * 675, "F"),
					new Beat(startTime + gap * 676, "J"),
					new Beat(startTime + gap * 678, "F"),
					new Beat(startTime + gap * 679, "J"),
					new Beat(startTime + gap * 680, "K"),
					new Beat(startTime + gap * 682, "F"),
					new Beat(startTime + gap * 683, "J"),
					new Beat(startTime + gap * 684, "D"),
					new Beat(startTime + gap * 686, "J"),
					new Beat(startTime + gap * 687, "F"),
					new Beat(startTime + gap * 688, "K"),
					new Beat(startTime + gap * 690, "D"),
					new Beat(startTime + gap * 692, "J"),
					new Beat(startTime + gap * 694, "F"), //2.10.697
					new Beat(startTime + gap * 696, "K"),
					new Beat(startTime + gap * 698, "D"),
					new Beat(startTime + gap * 700, "J"),
					new Beat(startTime + gap * 702, "F"),
					new Beat(startTime + gap * 704, "F"),
					new Beat(startTime + gap * 706, "K"),
					new Beat(startTime + gap * 707, "J"),
					new Beat(startTime + gap * 708, "F"),
					new Beat(startTime + gap * 710, "J"),
					new Beat(startTime + gap * 711, "F"),
					new Beat(startTime + gap * 712, "D"),
					new Beat(startTime + gap * 714, "F"),
					new Beat(startTime + gap * 715, "K"),
					new Beat(startTime + gap * 716, "D"),
					new Beat(startTime + gap * 718, "J"),
					new Beat(startTime + gap * 719, "D"),
					new Beat(startTime + gap * 720, "K"),
					new Beat(startTime + gap * 722, "J"),
					new Beat(startTime + gap * 724, "F"),
					new Beat(startTime + gap * 726, "D"),
					new Beat(startTime + gap * 728, "J"),
					new Beat(startTime + gap * 730, "K"),
					new Beat(startTime + gap * 732, "F"),
					new Beat(startTime + gap * 734, "J"),
					new Beat(startTime + gap * 736, "D"),
					new Beat(startTime + gap * 738, "K"),
					new Beat(startTime + gap * 739, "J"),
					new Beat(startTime + gap * 740, "F"),
					new Beat(startTime + gap * 742, "D"),
					new Beat(startTime + gap * 743, "F"),
					new Beat(startTime + gap * 744, "J"), //2.18.197
					new Beat(startTime + gap * 746, "K"),
					new Beat(startTime + gap * 747, "J"),
					new Beat(startTime + gap * 748, "F"),
					new Beat(startTime + gap * 750, "D"),
					new Beat(startTime + gap * 751, "K"),
					new Beat(startTime + gap * 752, "F"),
					new Beat(startTime + gap * 754, "J"),
					new Beat(startTime + gap * 756, "F"),
					new Beat(startTime + gap * 756, "K"),
					new Beat(startTime + gap * 758, "D"),
					new Beat(startTime + gap * 760, "F"),
					new Beat(startTime + gap * 762, "J"),
					new Beat(startTime + gap * 764, "D"),
					new Beat(startTime + gap * 764, "F"),
					new Beat(startTime + gap * 766, "K"),
					new Beat(startTime + gap * 768, "K"),
					new Beat(startTime + gap * 770, "D"),
					new Beat(startTime + gap * 771, "F"),
					new Beat(startTime + gap * 772, "K"),
					new Beat(startTime + gap * 774, "F"),
					new Beat(startTime + gap * 775, "D"),
					new Beat(startTime + gap * 776, "K"),
					new Beat(startTime + gap * 778, "F"),
					new Beat(startTime + gap * 779, "J"),
					new Beat(startTime + gap * 780, "K"),
					new Beat(startTime + gap * 782, "J"),
					new Beat(startTime + gap * 783, "F"),
					new Beat(startTime + gap * 784, "K"),
					new Beat(startTime + gap * 786, "D"), //1.24.497
					new Beat(startTime + gap * 788, "J"),
					new Beat(startTime + gap * 788, "K"),
					new Beat(startTime + gap * 790, "F"),
					new Beat(startTime + gap * 792, "D"),
					new Beat(startTime + gap * 794, "J"),
					new Beat(startTime + gap * 796, "D"),
					new Beat(startTime + gap * 796, "K"),
					new Beat(startTime + gap * 798, "F"),
					new Beat(startTime + gap * 800, "D"),
					new Beat(startTime + gap * 802, "K"),
					new Beat(startTime + gap * 803, "J"),
					new Beat(startTime + gap * 804, "D"),
					new Beat(startTime + gap * 806, "J"),
					new Beat(startTime + gap * 807, "K"),
					new Beat(startTime + gap * 808, "D"),
					new Beat(startTime + gap * 810, "F"),
					new Beat(startTime + gap * 811, "J"),
					new Beat(startTime + gap * 812, "D"),
					new Beat(startTime + gap * 814, "J"),
					new Beat(startTime + gap * 815, "F"),
					new Beat(startTime + gap * 816, "D"),
					new Beat(startTime + gap * 818, "F"),
					new Beat(startTime + gap * 820, "D"),
					new Beat(startTime + gap * 820, "J"),
					new Beat(startTime + gap * 822, "K"),
					new Beat(startTime + gap * 824, "F"),
					new Beat(startTime + gap * 826, "D"),
					new Beat(startTime + gap * 828, "J"),
					new Beat(startTime + gap * 828, "K"), //2.30.797
					new Beat(startTime + gap * 830, "F"),
					new Beat(startTime + gap * 832, "J"),
					new Beat(startTime + gap * 834, "F"),
					new Beat(startTime + gap * 835, "J"),
					new Beat(startTime + gap * 836, "K"),
					new Beat(startTime + gap * 838, "J"),
					new Beat(startTime + gap * 839, "F"),
					new Beat(startTime + gap * 840, "D"),
					new Beat(startTime + gap * 842, "K"),
					new Beat(startTime + gap * 843, "J"),
					new Beat(startTime + gap * 844, "F"),
					new Beat(startTime + gap * 846, "D"),
					new Beat(startTime + gap * 847, "F"),
					new Beat(startTime + gap * 848, "J"),
					new Beat(startTime + gap * 850, "D"),
					new Beat(startTime + gap * 852, "J"),
					new Beat(startTime + gap * 852, "K"),
					new Beat(startTime + gap * 854, "F"),
					new Beat(startTime + gap * 856, "K"),
					new Beat(startTime + gap * 858, "J"),
					new Beat(startTime + gap * 860, "D"),
					new Beat(startTime + gap * 860, "K"),
					new Beat(startTime + gap * 862, "F"),
					new Beat(startTime + gap * 864, "J"),
					new Beat(startTime + gap * 866, "D"),
					new Beat(startTime + gap * 867, "F"),
					new Beat(startTime + gap * 868, "J"),
					new Beat(startTime + gap * 870, "F"),
					new Beat(startTime + gap * 871, "J"),
					new Beat(startTime + gap * 872, "K"), //2.37.397
					new Beat(startTime + gap * 874, "J"),
					new Beat(startTime + gap * 875, "F"),
					new Beat(startTime + gap * 876, "D"),
					new Beat(startTime + gap * 878, "K"),
					new Beat(startTime + gap * 879, "J"),
					new Beat(startTime + gap * 880, "F"),
					new Beat(startTime + gap * 882, "D"),
					new Beat(startTime + gap * 884, "J"),
					new Beat(startTime + gap * 884, "K"),
					new Beat(startTime + gap * 886, "F"),
					new Beat(startTime + gap * 888, "J"),
					new Beat(startTime + gap * 890, "D"),
					new Beat(startTime + gap * 892, "F"),
					new Beat(startTime + gap * 892, "K"),
					new Beat(startTime + gap * 894, "J"),
			};
		}
		int i = 0;
		gameMusic.start();
		while(i < beats.length && !isInterrupted()) {
			boolean dropped = false;
			if(beats[i].getTime() <= gameMusic.getTime()) {
				Note note = new Note(beats[i].getNoteName());
				note.start();
				noteList.add(note);
				i++;
				dropped = true;
			}
			if(!dropped) {
				try {
					Thread.sleep(5);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//판정 함수
	public void judge(String input) {
		for(int i = 0; i < noteList.size(); i++) {
			Note note = noteList.get(i);
			if(input.equals(note.getNoteType())) {
				judgeEvent(note.judge());
				break;
			}
		}
	}
	
	public void judgeEvent(String judge) {
		if(judge.equals("Miss")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hitMiss.png")).getImage();
			Score += 0;
			ScorePrt = Integer.toString(Score);
		}
		else if(judge.equals("Fast")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hit50.png")).getImage();
			Score += 50;
			ScorePrt = Integer.toString(Score);
		}
		else if(judge.equals("Late")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hit50.png")).getImage();
			Score += 50;
			ScorePrt = Integer.toString(Score);
		}
		else if(judge.equals("Good")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hit100.png")).getImage();
			Score += 100;
			ScorePrt = Integer.toString(Score);
		}
		else if(judge.equals("Great")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hit200.png")).getImage();
			Score += 200;
			ScorePrt = Integer.toString(Score);
		}
		else if(judge.equals("Perfact")) {
			hitImage =new ImageIcon(Main.class.getResource("./images/hit300.png")).getImage();
			Score += 300;
			ScorePrt = Integer.toString(Score);
		}
	}
}
