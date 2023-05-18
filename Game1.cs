using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Nl2.Source.GamePlay;

namespace Nl2
{
    public class Game1 : Game
    {
        private GraphicsDeviceManager graphics;
        private SpriteBatch spriteBatch;
        World world;
        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
            IsMouseVisible = true;
        }

        protected override void Initialize()
        {
            // TODO: Add your initialization logic here

            base.Initialize();
        }

        protected override void LoadContent()
        {
            Global.content = this.Content;
            Global.spriteBatch = new SpriteBatch(GraphicsDevice);
            world = new World();
            Global.keyboard = new DlKeyboard();
            // TODO: use this.Content to load your game content here
        }

        protected override void Update(GameTime gameTime)
        {
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed || Keyboard.GetState().IsKeyDown(Keys.Escape))
                Exit();
            Global.keyboard.Update();
            world.UpDate();
            Global.keyboard.UpdateOld();
            // TODO: Add your update logic here

            base.Update(gameTime);
        }

        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            // TODO: Add your drawing code here

            Global.spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            world.Draw();
            Global.spriteBatch.End();
            base.Draw(gameTime);
        }
    }
}