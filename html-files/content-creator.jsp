<form action="/content-creator" method="POST">
    <label for="content-path">Content Path</label>
    <input type="text" name="contentPath" id="content-path"/>
    <br>
    <label for="properties">Properties (Optional)</label>
    <br>
    <span id="key-value-dest"></span>
    <button type="button" id="new-key-value">Add property key/value pair</button>
    <br>
    <button type="submit">Create</button>
</form>
<div id="key-value-pair" style="display:none;">
  <div>
    <input name="key-{{number}}" type="text" placeholder="key">
    <input name="value-{{number}}" type="text" placeholder="value">
  </div>
</div>
