package objectMap;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

import main.Game;
import objectMap.blocks.Block;
import utilities.Direction;

public class MapInteractor {

	private BlockMap blockMap;
	private boolean showGrid;
	private boolean placeable;
	private boolean currentlyPlacing;
	private Block blockToPlace;

	public boolean isPlaceable() {
		return placeable;
	}

	public void setPlaceable(boolean placeable) {
		this.placeable = placeable;
	}

	public Block getBlockToPlace() {
		return blockToPlace;
	}

	public void setBlockToPlace(Block blockToPlace) {
		this.blockToPlace = blockToPlace;
	}

	public PlacingType getPlacingType() {
		return placingType;
	}

	public void setPlacingType(PlacingType placingType) {
		this.placingType = placingType;
	}

	private PlacingType placingType;

	private final int cameraSpeed = 5;
	private Point originPosition;

	public MapInteractor(){
		blockMap = new BlockMap();
		blockMap.loadMap();

		showGrid = false;
		placeable = false;
		currentlyPlacing = false;

		placingType = PlacingType.NONE;


	}

	public void move(Direction d){

		Point pos = blockMap.getViewPosition();

		switch(d){
		case RIGHT:
			blockMap.setViewPosition(new Point(pos.x-cameraSpeed, pos.y));
			break;
		case LEFT:
			blockMap.setViewPosition(new Point(pos.x+cameraSpeed, pos.y));
			break;
		case UP:
			blockMap.setViewPosition(new Point(pos.x, pos.y-cameraSpeed));
			break;
		case DOWN:
			blockMap.setViewPosition(new Point(pos.x-cameraSpeed, pos.y+cameraSpeed));
			break;
		}
	}

	public void draw(Graphics2D g, Point mousePosition){

		blockMap.draw(g, showGrid);

		if (currentlyPlacing){
			Point viewPosition = blockMap.getViewPosition();
			int size = blockMap.getSize();
			switch(placingType){
			case LINE:
				if (Math.abs(mousePosition.x - originPosition.x) >= Math.abs(mousePosition.y - originPosition.y)){
					int mouseX = (int) ((mousePosition.x - viewPosition.x)/size);
					int originX = (int) ((originPosition.x - viewPosition.x)/size);
					int y = (int) ((originPosition.y - viewPosition.y)/size);
					if (mousePosition.x < originPosition.x){
						int temp = mouseX;
						mouseX = originX;
						originX = temp;
						mouseX++;

					}
					for (int x = originX; x < mouseX; x++){
						blockToPlace.draw(g, (x * size) + viewPosition.x, (y * size) + viewPosition.y, size);
					}

				} else {
					int mouseY = (int) ((mousePosition.y - viewPosition.y)/size);
					int originY = (int) ((originPosition.y - viewPosition.y)/size);
					int x = (int) ((originPosition.x - viewPosition.x)/size);
					if (mousePosition.y < originPosition.y){
						int temp = mouseY;
						mouseY = originY;
						originY = temp;
						mouseY++;
					}
					for (int y = originY; y < mouseY; y++){
						blockToPlace.draw(g, (x * size) + viewPosition.x, (y * size) + viewPosition.y, size);
					}
				}
				break;
			case SQUARE:
				int mouseX = (int) ((mousePosition.x - viewPosition.x)/size);
				int originX = (int) ((originPosition.x - viewPosition.x)/size);
				int mouseY = (int) ((mousePosition.y - viewPosition.y)/size);
				int originY = (int) ((originPosition.y - viewPosition.y)/size);

				if (mousePosition.x < originPosition.x){
					int temp = mouseX;
					mouseX = originX;
					originX = temp;
					mouseX++;

				}

				if (mousePosition.y < originPosition.y){
					int temp = mouseY;
					mouseY = originY;
					originY = temp;
					mouseY++;
				}

				for (int x = originX; x < mouseX; x++){
					for (int y = originY; y < mouseY; y++){
						blockToPlace.draw(g, (x * size) + viewPosition.x, (y * size) + viewPosition.y, size);
					}
				}
				break;
			case BUILDING:
				break;
			case NONE:
				break;
			case OBJECT:
				break;
			default:
				break;

			}
		}

		if (placeable){
			int size = blockMap.getSize();

			blockToPlace.draw(g, mousePosition.x - (size/2), mousePosition.y - (size/2), size);
		}
	}

	public void startPlacing(int x, int y) {
		if (placeable){
			currentlyPlacing = true;
			originPosition = new Point(x, y);
		}

	}

	public void endPlacing(int x, int y) {

		if (placeable){
			if (blockMap.contains(x, y) && blockMap.contains(originPosition.x, originPosition.y)){
				int size = blockMap.getSize();
				switch(placingType){
				case LINE:
					if (Math.abs(mousePosition.x - originPlacingPoint.x) >= Math.abs(mousePosition.y - originPlacingPoint.y)){
						int mouseX = x;
						int originX = originPlacingPoint.x;


						if (mouseX < originX){
							int temp = mouseX;
							mouseX = originX;
							originX = temp;
							mouseX+=size/2;

						}
						for (int placingX = originX; placingX < mouseX; placingX+=size){
							blockMap.place(placingX, originPlacingPoint.y, blockToPlace);
						}

					} else {
						int mouseY = y;
						int originY = originPlacingPoint.y;

						if (mouseY < originY){
							int temp = mouseY;
							mouseY = originY;
							originY = temp;
							mouseY+=size/2;

						}
						for (int placingY = originY; placingY < mouseY; placingY+=size){
							blockMap.place(originPlacingPoint.x, placingY, blockToPlace);
						}
					}
				case SQUARE:

					int mouseX = x;
					int originX = originPlacingPoint.x;
					int mouseY = y;
					int originY = originPlacingPoint.y;

					if (mouseX < originX){
						int temp = mouseX;
						mouseX = originX;
						originX = temp;
						mouseX+=size/2;

					}

					if (mouseY < originY){
						int temp = mouseY;
						mouseY = originY;
						originY = temp;
						mouseY+=size/2;

					}
					for (int placingX = originX; placingX < mouseX; placingX+=size){
						for (int placingY = originY; placingY < mouseY; placingY+=size){
							blockMap.place(placingX, placingY, blockToPlace);
						}
					}

				}

				currentlyPlacing = false;
				if (!shiftHeld){
					placeable = false;
				}
			}
		}

	}

	public void zoom(int x, int y, int notches) {
		int size = blockMap.getSize();

		if (notches < 0){
			if (size < 100){
				blockMap.setSize(blockMap.getSize() + 1);

				int newX = Math.round((e.getX() * 100/Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();


				blockMap.setViewPosition(new Point(oldPos.x - newX, oldPos.y - newY));
			}
		}else{
			if (size > 4){
				blockMap.setSize(blockMap.getSize() - 1);


				int newX = Math.round((e.getX() * 100 /Game.WIDTH));
				int newY = Math.round((e.getY() * 100/Game.HEIGHT));

				Point oldPos = blockMap.getViewPosition();

				blockMap.setViewPosition(new Point(oldPos.x + newX, oldPos.y + newY));
			}
		}



	}



}
